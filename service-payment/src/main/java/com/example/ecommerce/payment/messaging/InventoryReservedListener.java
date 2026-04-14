package com.example.ecommerce.payment.messaging;

import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedListener {

    private final PaymentService paymentService;

    public InventoryReservedListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "inventory-reserved", groupId = "payment-service")
    public void onInventoryReserved(InventoryReservedEvent event) {
        paymentService.process(event).join();
    }
}
