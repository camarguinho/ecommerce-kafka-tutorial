package com.example.ecommerce.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.UUID;

/**
 * Event used as functional DLQ payload for business failures.
 */
@JsonTypeName("order-failed")
public record OrderFailedEvent(
        UUID eventId,
        EventType type,
        Instant occurredAt,
        UUID orderId,
        String source,
        String reason
) implements EcommerceEvent {
}
