package com.example.ecommerce.payment.client;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayClient {

    public String charge(BigDecimal amount, boolean simulateFailure) {
        if (simulateFailure) {
            throw new IllegalStateException("Simulated payment gateway outage");
        }
        return "PG-" + amount.toPlainString().replace('.', '-');
    }
}
