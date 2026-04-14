package com.example.ecommerce.inventory.service;

import com.example.ecommerce.events.EventType;
import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.events.OrderCreatedEvent;
import com.example.ecommerce.events.OrderFailedEvent;
import com.example.ecommerce.inventory.domain.InventoryReservation;
import com.example.ecommerce.inventory.domain.Product;
import com.example.ecommerce.inventory.messaging.InventoryEventPublisher;
import com.example.ecommerce.inventory.repository.InventoryReservationRepository;
import com.example.ecommerce.inventory.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
/**
 * Reserves stock and emits either success or failure events for the order flow.
 */
public class InventoryService {

    private final ProductRepository productRepository;
    private final InventoryReservationRepository reservationRepository;
    private final InventoryEventPublisher eventPublisher;

    public InventoryService(ProductRepository productRepository,
                            InventoryReservationRepository reservationRepository,
                            InventoryEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void reserve(OrderCreatedEvent event) {
        for (var item : event.items()) {
            Product product = productRepository.findById(item.productId()).orElse(null);
            if (product == null || product.getStockQuantity() < item.quantity()) {
                eventPublisher.publishFailed(new OrderFailedEvent(
                        UUID.randomUUID(),
                        EventType.ORDER_FAILED,
                        Instant.now(),
                        event.orderId(),
                        "inventory-service",
                        "Insufficient stock for product " + item.productId()
                ));
                return;
            }
        }

        for (var item : event.items()) {
            Product product = productRepository.findById(item.productId()).orElseThrow();
            product.decreaseStock(item.quantity());
            reservationRepository.save(new InventoryReservation(
                    UUID.randomUUID(),
                    event.orderId(),
                    item.productId(),
                    item.quantity(),
                    Instant.now()
            ));
        }

        eventPublisher.publishReserved(new InventoryReservedEvent(
                UUID.randomUUID(),
                EventType.INVENTORY_RESERVED,
                Instant.now(),
                event.orderId(),
                "RES-" + event.orderId().toString().substring(0, 8),
                event.simulatePaymentFailure()
        ));
    }
}
