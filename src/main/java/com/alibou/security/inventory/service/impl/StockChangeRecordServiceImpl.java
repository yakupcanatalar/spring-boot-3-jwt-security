package com.alibou.security.inventory.service.impl;

import com.alibou.security.inventory.model.StockChangeRecord;
import com.alibou.security.inventory.model.StockChangeRecordRequest;
import com.alibou.security.inventory.model.StockChangeType;
import com.alibou.security.inventory.repository.StockChangeRecordRepository;
import com.alibou.security.inventory.service.SnowFlakeIdGenerator;
import com.alibou.security.inventory.service.StockChangeRecordService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StockChangeRecordServiceImpl implements StockChangeRecordService {

    private final Logger logger = LoggerFactory.getLogger(StockChangeRecordServiceImpl.class);

    private final StockChangeRecordRepository repository;
    private final SnowFlakeIdGenerator snowFlakeIdGenerator;

    public StockChangeRecordServiceImpl(StockChangeRecordRepository repository,
                                        @Qualifier("stockChangeRecordSnowFlakeIdGenerator") SnowFlakeIdGenerator snowFlakeIdGenerator) {
        this.repository = repository;
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
    }

    @NotNull
    @Override
    public StockChangeRecord save(long userId, @NotNull StockChangeType type, @NotNull StockChangeRecordRequest request) {
        logger.info("User = {} saving stock change record with for type = {} and request = {}", userId, type, request);
        final StockChangeRecord record = new StockChangeRecord();
        final long id = snowFlakeIdGenerator.generateId();
        record.setId(id);
        record.setUserId(userId);
        record.setBarcode(request.barcode());
        record.setChangeType(type);
        record.setOldQuantity(request.oldQty());
        record.setCurrentQuantity(request.currentQty());
        record.setQty(request.qty());
        return repository.save(record);
    }
}
