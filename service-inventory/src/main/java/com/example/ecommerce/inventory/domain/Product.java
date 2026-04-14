package com.example.ecommerce.inventory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    protected Product() {
    }

    public UUID getId() {
        return id;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void decreaseStock(int quantity) {
        this.stockQuantity = this.stockQuantity - quantity;
    }
}
