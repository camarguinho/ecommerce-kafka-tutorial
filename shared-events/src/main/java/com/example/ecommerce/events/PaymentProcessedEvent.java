package com.example.ecommerce.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Event emitted when payment is approved.
 */
@JsonTypeName("payment-processed")
public record PaymentProcessedEvent(
        UUID eventId,
        EventType type,
        Instant occurredAt,
        UUID orderId,
        UUID paymentId,
        BigDecimal amount
) implements EcommerceEvent {
}
