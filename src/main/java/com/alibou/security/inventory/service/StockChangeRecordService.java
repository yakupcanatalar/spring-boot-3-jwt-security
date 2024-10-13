package com.alibou.security.inventory.service;

import com.alibou.security.inventory.model.StockChangeRecord;
import com.alibou.security.inventory.model.StockChangeRecordRequest;
import com.alibou.security.inventory.model.StockChangeType;
import org.jetbrains.annotations.NotNull;

public interface StockChangeRecordService {

    @NotNull
    StockChangeRecord save(long userId, @NotNull StockChangeType type, @NotNull StockChangeRecordRequest request);
}
