package com.dkarlov.catalog.service.impl;

import com.dkarlov.catalog.dao.ProductRepository;
import com.dkarlov.catalog.dto.ProductInventoryResponse;
import com.dkarlov.catalog.model.Product;
import com.dkarlov.catalog.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    private static final String INVENTORY_URL = "http://inventory-service/api/inventories/{code}";

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
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

            ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                    restTemplate.getForEntity(INVENTORY_URL, ProductInventoryResponse.class, code);

            if (itemResponseEntity.getStatusCode() == OK) {
                int quantity = itemResponseEntity.getBody().getAvailableQuantity();
                log.info("Available quantity: {}", quantity);
                productOptional.get().setInStock(quantity > 0);
            } else {
                log.error("Unable to get inventory level for product code: {}, StatusCode: {}",
                        code, itemResponseEntity.getStatusCode());
            }
        }

        return productOptional;
    }
}
