package com.example.ecommerce.order.dto;

import com.example.ecommerce.order.domain.OrderStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponse(UUID orderId, UUID customerId, BigDecimal totalAmount, OrderStatus status) {
}
