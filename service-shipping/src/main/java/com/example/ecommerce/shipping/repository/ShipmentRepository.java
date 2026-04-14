package com.example.ecommerce.shipping.repository;

import com.example.ecommerce.shipping.domain.Shipment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
}
