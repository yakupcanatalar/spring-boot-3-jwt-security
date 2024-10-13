package com.alibou.security.inventory.service;

import com.alibou.security.inventory.model.Stock;
import com.alibou.security.inventory.model.UploadStockRequest;
import org.jetbrains.annotations.NotNull;

public interface StockService {

    @NotNull
    Stock upload(long userId, @NotNull String barcode, @NotNull UploadStockRequest uploadRequest);

    @NotNull
    Stock get(long userId, @NotNull String barcode);
}
