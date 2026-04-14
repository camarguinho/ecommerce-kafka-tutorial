package com.example.ecommerce.inventory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inventory_reservations")
public class InventoryReservation {

    @Id
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(nullable = false)
    private String status;

    @Column(name = "reserved_at", nullable = false)
    private Instant reservedAt;

    protected InventoryReservation() {
    }

    public InventoryReservation(UUID id, UUID orderId, UUID productId, Integer reservedQuantity, Instant reservedAt) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.reservedQuantity = reservedQuantity;
        this.status = "RESERVED";
        this.reservedAt = reservedAt;
    }
}
