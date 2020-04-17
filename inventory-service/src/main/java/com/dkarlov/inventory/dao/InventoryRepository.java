package com.dkarlov.inventory.dao;

import com.dkarlov.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByProductCode(String productCode);
}
