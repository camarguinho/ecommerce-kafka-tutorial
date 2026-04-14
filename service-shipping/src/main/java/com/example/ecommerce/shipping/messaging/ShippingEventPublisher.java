package com.example.ecommerce.shipping.messaging;

import com.example.ecommerce.events.OrderConfirmedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShippingEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ShippingEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderConfirmed(OrderConfirmedEvent event) {
        kafkaTemplate.send("order-confirmed", event.orderId().toString(), event);
    }
}
