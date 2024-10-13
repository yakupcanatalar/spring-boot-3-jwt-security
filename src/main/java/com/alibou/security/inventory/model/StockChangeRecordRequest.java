package com.alibou.security.inventory.model;

import org.jetbrains.annotations.NotNull;

public record StockChangeRecordRequest(long oldQty, long currentQty, int qty, @NotNull String barcode) {
}
