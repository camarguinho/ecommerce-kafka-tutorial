package com.example.ecommerce.inventory.repository;

import com.example.ecommerce.inventory.domain.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
