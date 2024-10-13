package com.alibou.security.inventory.controller;

import com.alibou.security.inventory.model.Stock;
import com.alibou.security.inventory.model.UploadStockRequest;
import com.alibou.security.inventory.service.StockService;
import com.alibou.security.user.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping("/{barcode}")
    public Stock get(@AuthenticationPrincipal User user,
                     @PathVariable String barcode) {
        return service.get(user.getId(), barcode);
    }

    @PutMapping("/{barcode}")
    public Stock upload(@AuthenticationPrincipal User user,
                        @PathVariable String barcode,
                        @RequestBody UploadStockRequest request) {
        return service.upload(user.getId(), barcode, request);
    }

}
