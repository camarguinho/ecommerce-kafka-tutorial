package com.example.ecommerce.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequest(
        @NotNull UUID productId,
        @NotNull @Min(1) Integer quantity,
        @NotNull BigDecimal unitPrice
) {
}
