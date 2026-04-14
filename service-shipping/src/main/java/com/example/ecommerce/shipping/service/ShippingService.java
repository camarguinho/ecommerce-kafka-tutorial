package com.example.ecommerce.shipping.service;

import com.example.ecommerce.events.EventType;
import com.example.ecommerce.events.OrderConfirmedEvent;
import com.example.ecommerce.events.PaymentProcessedEvent;
import com.example.ecommerce.shipping.domain.Shipment;
import com.example.ecommerce.shipping.messaging.ShippingEventPublisher;
import com.example.ecommerce.shipping.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShipmentRepository shipmentRepository;
    private final ShippingEventPublisher shippingEventPublisher;

    public ShippingService(ShipmentRepository shipmentRepository, ShippingEventPublisher shippingEventPublisher) {
        this.shipmentRepository = shipmentRepository;
        this.shippingEventPublisher = shippingEventPublisher;
    }

    @Transactional
    public void schedule(PaymentProcessedEvent event) {
        Shipment shipment = shipmentRepository.save(new Shipment(
                UUID.randomUUID(),
                event.orderId(),
                "TRK-" + event.orderId().toString().substring(0, 8),
                Instant.now()
        ));

        shippingEventPublisher.publishOrderConfirmed(new OrderConfirmedEvent(
                UUID.randomUUID(),
                EventType.ORDER_CONFIRMED,
                Instant.now(),
                event.orderId(),
                shipment.getId(),
                shipment.getTrackingCode()
        ));
    }
}
