package com.dkarlov.catalog.service;

import com.dkarlov.catalog.dto.ProductInventoryResponse;

import java.util.Optional;

public interface InventoryClientService {
    Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode);
}
