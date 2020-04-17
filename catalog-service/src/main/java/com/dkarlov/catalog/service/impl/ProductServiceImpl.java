package com.dkarlov.catalog.service.impl;

import com.dkarlov.catalog.dao.ProductRepository;
import com.dkarlov.catalog.dto.ProductInventoryResponse;
import com.dkarlov.catalog.model.Product;
import com.dkarlov.catalog.service.InventoryClientService;
import com.dkarlov.catalog.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final InventoryClientService inventoryClientService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(InventoryClientService inventoryClientService, ProductRepository productRepository) {
        this.inventoryClientService = inventoryClientService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);

        if (productOptional.isPresent()) {
            log.info("Fetching inventory level for product code: {}", code);

            Optional<ProductInventoryResponse> itemResponseEntity = inventoryClientService.getProductInventoryByCode(code);
            itemResponseEntity.ifPresent(productInventoryResponse ->
                    productOptional.get().setInStock(productInventoryResponse.getAvailableQuantity() > 0));
        }

        return productOptional;
    }
}
