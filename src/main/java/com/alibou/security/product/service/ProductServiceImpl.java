package com.alibou.security.product.service;

import com.alibou.security.product.model.Product;
import com.alibou.security.product.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public Product getProduct(long userId, @NotNull String barcode) {
        logger.info("User = {} request product for barcode = {}", userId, barcode);
        return repository.findById(barcode).orElseThrow(() -> {
            final String errorMessage = String.format("Product not found for barcode %s", barcode);
            return new RuntimeException(errorMessage);
        });
    }
}
