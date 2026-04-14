package com.example.ecommerce.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.UUID;

/**
 * Event emitted when shipment is created and order can be considered confirmed.
 */
@JsonTypeName("order-confirmed")
public record OrderConfirmedEvent(
        UUID eventId,
        EventType type,
        Instant occurredAt,
        UUID orderId,
        UUID shipmentId,
        String trackingCode
) implements EcommerceEvent {
}
