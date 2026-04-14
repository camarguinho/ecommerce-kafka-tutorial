package com.example.ecommerce.events;

import java.time.Instant;
import java.util.UUID;

/**
 * Sealed base contract for all Kafka events exchanged between services.
 */
public sealed interface EcommerceEvent permits OrderCreatedEvent, InventoryReservedEvent, PaymentProcessedEvent, OrderConfirmedEvent, OrderFailedEvent {
    UUID eventId();
    EventType type();
    Instant occurredAt();
    UUID orderId();
}
