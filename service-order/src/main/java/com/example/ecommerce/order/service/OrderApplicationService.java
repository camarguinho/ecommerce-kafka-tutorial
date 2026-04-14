package com.example.ecommerce.order.service;

import com.example.ecommerce.events.EventType;
import com.example.ecommerce.events.OrderCreatedEvent;
import com.example.ecommerce.events.OrderItemSnapshot;
import com.example.ecommerce.order.domain.CustomerOrder;
import com.example.ecommerce.order.domain.OrderItem;
import com.example.ecommerce.order.domain.OrderStatus;
import com.example.ecommerce.order.dto.CreateOrderRequest;
import com.example.ecommerce.order.dto.OrderResponse;
import com.example.ecommerce.order.messaging.OrderEventPublisher;
import com.example.ecommerce.order.repository.CustomerOrderRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
/**
 * Orchestrates order creation and local state transitions based on incoming events.
 */
public class OrderApplicationService {

    private final CustomerOrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderApplicationService(CustomerOrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        UUID orderId = UUID.randomUUID();
        Instant now = Instant.now();
        BigDecimal total = request.items().stream()
                .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CustomerOrder order = new CustomerOrder(orderId, request.customerId(), total, now);
        request.items().forEach(item -> order.addItem(new OrderItem(
                UUID.randomUUID(),
                order,
                item.productId(),
                item.quantity(),
                item.unitPrice()
        )));

        CustomerOrder saved = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID(),
                EventType.ORDER_CREATED,
                now,
                saved.getId(),
                saved.getCustomerId(),
                saved.getItems().stream()
                        .map(item -> new OrderItemSnapshot(item.getProductId(), item.getQuantity(), item.getUnitPrice()))
                        .toList(),
                saved.getTotalAmount(),
                request.simulatePaymentFailure()
        );
        orderEventPublisher.publishOrderCreated(event);
        return new OrderResponse(saved.getId(), saved.getCustomerId(), saved.getTotalAmount(), saved.getStatus());
    }

    @Transactional
    public void updateStatus(UUID orderId, OrderStatus status) {
        orderRepository.findById(orderId).ifPresent(order -> order.updateStatus(status));
    }
}
