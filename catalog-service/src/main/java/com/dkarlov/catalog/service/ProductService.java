package com.dkarlov.catalog.service;

import com.dkarlov.catalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findProductByCode(String code);
}
