package com.dkarlov.inventory.service.impl;

import com.dkarlov.inventory.dao.InventoryRepository;
import com.dkarlov.inventory.model.InventoryItem;
import com.dkarlov.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Optional<InventoryItem> findInventoryItemByCode(String productCode) {
        return inventoryRepository.findByProductCode(productCode);
    }
}
