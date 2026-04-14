package com.example.ecommerce.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.ecommerce.order.domain.CustomerOrder;
import com.example.ecommerce.order.dto.CreateOrderRequest;
import com.example.ecommerce.order.dto.OrderItemRequest;
import com.example.ecommerce.order.messaging.OrderEventPublisher;
import com.example.ecommerce.order.repository.CustomerOrderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderApplicationServiceTest {

    @Mock
    private CustomerOrderRepository repository;

    @Mock
    private OrderEventPublisher publisher;

    @InjectMocks
    private OrderApplicationService service;

    @Test
    void shouldCreateOrderAndPublishEvent() {
        CreateOrderRequest request = new CreateOrderRequest(
                UUID.randomUUID(),
                false,
                List.of(new OrderItemRequest(UUID.randomUUID(), 2, BigDecimal.valueOf(25.0)))
        );

        when(repository.save(any(CustomerOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = service.createOrder(request);

        assertThat(response.totalAmount()).isEqualByComparingTo("50.0");
        assertThat(response.orderId()).isNotNull();
        verify(publisher).publishOrderCreated(any());
    }
}
