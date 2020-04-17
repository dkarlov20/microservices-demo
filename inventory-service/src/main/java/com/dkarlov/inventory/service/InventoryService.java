package com.dkarlov.inventory.service;

import com.dkarlov.inventory.model.InventoryItem;

import java.util.Optional;

public interface InventoryService {
    Optional<InventoryItem> findInventoryItemByCode(String productCode);
}
