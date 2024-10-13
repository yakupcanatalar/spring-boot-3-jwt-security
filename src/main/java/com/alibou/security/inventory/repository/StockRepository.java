package com.alibou.security.inventory.repository;

import com.alibou.security.inventory.model.Stock;
import com.alibou.security.inventory.model.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {
}
