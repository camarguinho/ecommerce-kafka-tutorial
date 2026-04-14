package com.example.ecommerce.events;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Immutable snapshot of an order line sent through Kafka.
 */
public record OrderItemSnapshot(UUID productId, Integer quantity, BigDecimal unitPrice) {
}
