package com.example.ecommerce.order.messaging;

import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.events.OrderConfirmedEvent;
import com.example.ecommerce.events.OrderFailedEvent;
import com.example.ecommerce.events.PaymentProcessedEvent;
import com.example.ecommerce.order.domain.OrderStatus;
import com.example.ecommerce.order.service.OrderApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);

    private final OrderApplicationService orderService;

    public OrderEventListener(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "inventory-reserved", groupId = "order-service")
    public void onInventoryReserved(InventoryReservedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.INVENTORY_RESERVED);
        log.info("Order {} moved to INVENTORY_RESERVED", event.orderId());
    }

    @KafkaListener(topics = "payment-processed", groupId = "order-service")
    public void onPaymentProcessed(PaymentProcessedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.PAYMENT_CONFIRMED);
        log.info("Order {} moved to PAYMENT_CONFIRMED", event.orderId());
    }

    @KafkaListener(topics = "order-confirmed", groupId = "order-service")
    public void onOrderConfirmed(OrderConfirmedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.CONFIRMED);
        log.info("Order {} moved to CONFIRMED", event.orderId());
    }

    @KafkaListener(topics = "order-failed", groupId = "order-service")
    public void onOrderFailed(OrderFailedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.FAILED);
        log.warn("Order {} moved to FAILED due to {}", event.orderId(), event.reason());
    }
}
