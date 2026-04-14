package com.example.ecommerce.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Event emitted when an order is accepted by the order service.
 */
@JsonTypeName("order-created")
public record OrderCreatedEvent(
        UUID eventId,
        EventType type,
        Instant occurredAt,
        UUID orderId,
        UUID customerId,
        List<OrderItemSnapshot> items,
        BigDecimal totalAmount,
        boolean simulatePaymentFailure
) implements EcommerceEvent {
}
