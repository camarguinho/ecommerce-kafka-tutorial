package com.example.ecommerce.shipping.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    private UUID id;

    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "tracking_code", nullable = false)
    private String trackingCode;

    @Column(nullable = false)
    private String status;

    @Column(name = "shipped_at", nullable = false)
    private Instant shippedAt;

    protected Shipment() {
    }

    public Shipment(UUID id, UUID orderId, String trackingCode, Instant shippedAt) {
        this.id = id;
        this.orderId = orderId;
        this.trackingCode = trackingCode;
        this.status = "IN_TRANSIT";
        this.shippedAt = shippedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }
}
