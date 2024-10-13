package com.alibou.security.inventory.service.impl;

import com.alibou.security.inventory.model.*;
import com.alibou.security.inventory.repository.StockRepository;
import com.alibou.security.inventory.service.StockChangeRecordService;
import com.alibou.security.inventory.service.StockService;
import com.alibou.security.product.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class StockServiceImpl implements StockService {

    private final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
    private final StockRepository repository;
    private final ProductService productService;
    private final StockChangeRecordService stockChangeRecordService;

    public StockServiceImpl(StockRepository repository,
                            ProductService productService,
                            StockChangeRecordService stockChangeRecordService) {
        this.repository = repository;
        this.productService = productService;
        this.stockChangeRecordService = stockChangeRecordService;
    }

    @Transactional
    @NotNull
    @Override
    public Stock upload(long userId, @NotNull String barcode, @NotNull UploadStockRequest uploadRequest) {
        logger.info("User = {} uploading stock with for product = {} request = {}", userId, barcode, uploadRequest);
        final int qty = uploadRequest.qty();
        final StockId stockId = new StockId(userId, barcode);
        productService.getProduct(userId, barcode);
        final AtomicLong oldQty = new AtomicLong(0);
        final Stock stock = repository.findById(stockId).map(existStock -> {
            final long existQty = existStock.getQuantity();
            oldQty.set(existQty);
            existStock.setQuantity(existQty + qty);
            return existStock;
        }).orElseGet(() -> {
            logger.info("Stock for product = {} does not exist. New stock created", barcode);
            final Stock newStock = new Stock();
            newStock.setUserId(userId);
            newStock.setBarcode(barcode);
            newStock.setQuantity(qty);
            return newStock;
        });
        final StockChangeRecordRequest stockChangeRecordRequest = new StockChangeRecordRequest(oldQty.get(), stock.getQuantity(), uploadRequest.qty(), barcode);
        stockChangeRecordService.save(userId, StockChangeType.UPLOAD, stockChangeRecordRequest);
        return repository.save(stock);
    }

    @NotNull
    @Override
    public Stock get(long userId, @NotNull String barcode) {
        logger.info("User = {} getting stock = {}", userId, barcode);
        productService.getProduct(userId, barcode);
        return repository.findById(new StockId(userId, barcode))
                .orElseGet(() -> {
                    logger.info("Stock for product = {} does not exist. Empty stock created", barcode);
                    final Stock newStock = new Stock();
                    newStock.setUserId(userId);
                    newStock.setBarcode(barcode);
                    return repository.save(newStock);
                });
    }
}
