package com.example.ecommerce.inventory.messaging;

import com.example.ecommerce.events.OrderCreatedEvent;
import com.example.ecommerce.inventory.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final InventoryService inventoryService;

    public OrderCreatedListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void onOrderCreated(OrderCreatedEvent event) {
        inventoryService.reserve(event);
    }
}
