package com.alibou.security.product.service;

import com.alibou.security.product.model.Product;
import org.jetbrains.annotations.NotNull;

public interface ProductService {

    @NotNull
    Product getProduct(long userId, @NotNull String barcode);

}
