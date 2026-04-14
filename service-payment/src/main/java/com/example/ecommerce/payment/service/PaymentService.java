package com.example.ecommerce.payment.service;

import com.example.ecommerce.events.EventType;
import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.events.OrderFailedEvent;
import com.example.ecommerce.events.PaymentProcessedEvent;
import com.example.ecommerce.payment.client.PaymentGatewayClient;
import com.example.ecommerce.payment.domain.Payment;
import com.example.ecommerce.payment.domain.PaymentStatus;
import com.example.ecommerce.payment.messaging.PaymentEventPublisher;
import com.example.ecommerce.payment.repository.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
/**
 * Applies resilience patterns while processing payments and publishing outcome events.
 */
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentGatewayClient paymentGatewayClient;
    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    public PaymentService(PaymentGatewayClient paymentGatewayClient,
                          PaymentRepository paymentRepository,
                          PaymentEventPublisher paymentEventPublisher) {
        this.paymentGatewayClient = paymentGatewayClient;
        this.paymentRepository = paymentRepository;
        this.paymentEventPublisher = paymentEventPublisher;
    }

    @Transactional
    @Retry(name = "paymentGateway")
    @CircuitBreaker(name = "paymentGateway", fallbackMethod = "fallback")
    @TimeLimiter(name = "paymentGateway")
    public CompletableFuture<Void> process(InventoryReservedEvent event) {
        return CompletableFuture.runAsync(() -> {
            String gatewayReference = paymentGatewayClient.charge(java.math.BigDecimal.valueOf(100), event.simulatePaymentFailure());
            Payment payment = new Payment(
                    UUID.randomUUID(),
                    event.orderId(),
                    java.math.BigDecimal.valueOf(100),
                    PaymentStatus.APPROVED,
                    Instant.now()
            );
            paymentRepository.save(payment);
            paymentEventPublisher.publishProcessed(new PaymentProcessedEvent(
                    UUID.randomUUID(),
                    EventType.PAYMENT_PROCESSED,
                    Instant.now(),
                    event.orderId(),
                    payment.getId(),
                    java.math.BigDecimal.valueOf(100)
            ));
            log.info("Payment approved for order={} ref={}", event.orderId(), gatewayReference);
        });
    }

    @Transactional
    public CompletableFuture<Void> fallback(InventoryReservedEvent event, Throwable throwable) {
        paymentEventPublisher.publishFailed(new OrderFailedEvent(
                UUID.randomUUID(),
                EventType.ORDER_FAILED,
                Instant.now(),
                event.orderId(),
                "payment-service",
                throwable.getMessage()
        ));
        log.error("Payment failed for order={} after resilience policies", event.orderId(), throwable);
        return CompletableFuture.completedFuture(null);
    }
}
