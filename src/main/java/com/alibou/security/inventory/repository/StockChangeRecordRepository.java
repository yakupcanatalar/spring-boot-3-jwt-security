package com.alibou.security.inventory.repository;

import com.alibou.security.inventory.model.StockChangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockChangeRecordRepository extends JpaRepository<StockChangeRecord, Long> {
}
