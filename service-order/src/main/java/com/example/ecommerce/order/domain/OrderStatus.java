package com.example.ecommerce.order.domain;

public enum OrderStatus {
    PENDING,
    INVENTORY_RESERVED,
    PAYMENT_CONFIRMED,
    CONFIRMED,
    FAILED
}
