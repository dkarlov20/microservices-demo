package com.dkarlov.catalog.service.impl;

import com.dkarlov.catalog.dto.ProductInventoryResponse;
import com.dkarlov.catalog.service.InventoryClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
@Slf4j
public class InventoryClientServiceImpl implements InventoryClientService {
    private final RestTemplate restTemplate;

    @Value("${api.inventory-service}")
    private String inventoryServiceApi;

    @Autowired
    public InventoryClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode")
    public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode) {
        ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                restTemplate.getForEntity(inventoryServiceApi, ProductInventoryResponse.class, productCode);

        if (itemResponseEntity.getStatusCode() == OK) {
            return Optional.ofNullable(itemResponseEntity.getBody());
        } else {
            log.error("Unable to get inventory level for product code: {}, StatusCode: {}",
                    productCode, itemResponseEntity.getStatusCode());
            return Optional.empty();
        }
    }

    private Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {
        log.info("Returning default ProductInventoryResponse for product code: " + productCode);

        ProductInventoryResponse response = new ProductInventoryResponse();
        response.setProductCode(productCode);
        response.setAvailableQuantity(0);

        return Optional.of(response);
    }
}