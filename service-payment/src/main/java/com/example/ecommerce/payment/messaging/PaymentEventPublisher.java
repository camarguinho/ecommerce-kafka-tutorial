package com.example.ecommerce.payment.messaging;

import com.example.ecommerce.events.OrderFailedEvent;
import com.example.ecommerce.events.PaymentProcessedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishProcessed(PaymentProcessedEvent event) {
        kafkaTemplate.send("payment-processed", event.orderId().toString(), event);
    }

    public void publishFailed(OrderFailedEvent event) {
        kafkaTemplate.send("order-failed", event.orderId().toString(), event);
    }
}
