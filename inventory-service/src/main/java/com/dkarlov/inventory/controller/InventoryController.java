package com.dkarlov.inventory.controller;

import com.dkarlov.inventory.exception.InventoryItemNotFoundException;
import com.dkarlov.inventory.model.InventoryItem;
import com.dkarlov.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productCode}")
    public InventoryItem findInventoryByProductCode(@PathVariable("productCode") String productCode) {
        log.info("Finding inventory item for product code: {}", productCode);

        return inventoryService.findInventoryItemByCode(productCode)
                .orElseThrow(() -> new InventoryItemNotFoundException("Inventory with product code [" + productCode + "] doesn't exist"));
    }
}
