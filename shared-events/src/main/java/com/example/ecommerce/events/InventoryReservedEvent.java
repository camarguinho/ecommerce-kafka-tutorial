package com.example.ecommerce.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.UUID;

/**
 * Event emitted when inventory is successfully reserved.
 */
@JsonTypeName("inventory-reserved")
public record InventoryReservedEvent(
        UUID eventId,
        EventType type,
        Instant occurredAt,
        UUID orderId,
        String reservationCode,
        boolean simulatePaymentFailure
) implements EcommerceEvent {
}
