package com.example.ecommerce.order.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.ecommerce.order.repository.CustomerOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OrderLiquibaseIntegrationTest {

    @Autowired
    private CustomerOrderRepository repository;

    @Test
    void shouldLoadContextAndInitializeLiquibaseSchema() {
        assertThat(repository.count()).isZero();
    }
}
