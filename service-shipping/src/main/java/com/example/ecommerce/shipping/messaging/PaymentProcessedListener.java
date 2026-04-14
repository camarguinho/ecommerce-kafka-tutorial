package com.example.ecommerce.shipping.messaging;

import com.example.ecommerce.events.PaymentProcessedEvent;
import com.example.ecommerce.shipping.service.ShippingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedListener {

    private final ShippingService shippingService;

    public PaymentProcessedListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @KafkaListener(topics = "payment-processed", groupId = "shipping-service")
    public void onPaymentProcessed(PaymentProcessedEvent event) {
        shippingService.schedule(event);
    }
}
