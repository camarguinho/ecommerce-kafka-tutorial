package com.example.ecommerce.inventory.repository;

import com.example.ecommerce.inventory.domain.InventoryReservation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryReservationRepository extends JpaRepository<InventoryReservation, UUID> {
}
