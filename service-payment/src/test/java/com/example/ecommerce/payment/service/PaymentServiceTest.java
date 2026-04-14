package com.example.ecommerce.payment.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.ecommerce.events.EventType;
import com.example.ecommerce.events.InventoryReservedEvent;
import com.example.ecommerce.payment.messaging.PaymentEventPublisher;
import com.example.ecommerce.payment.repository.PaymentRepository;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentEventPublisher publisher;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void fallbackShouldPublishOrderFailedEvent() {
        InventoryReservedEvent event = new InventoryReservedEvent(
                UUID.randomUUID(),
                EventType.INVENTORY_RESERVED,
                Instant.now(),
                UUID.randomUUID(),
                "RES-1",
                true
        );

        paymentService.fallback(event, new IllegalStateException("gateway error"));

        verify(publisher).publishFailed(any());
    }
}
