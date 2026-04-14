package com.example.ecommerce.inventory.messaging;

import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.events.OrderFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(InventoryEventPublisher.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReserved(InventoryReservedEvent event) {
        kafkaTemplate.send("inventory-reserved", event.orderId().toString(), event);
        log.info("Published inventory-reserved for orderId={}", event.orderId());
    }

    public void publishFailed(OrderFailedEvent event) {
        kafkaTemplate.send("order-failed", event.orderId().toString(), event);
        log.warn("Published order-failed from inventory for orderId={} reason={}", event.orderId(), event.reason());
    }
}
