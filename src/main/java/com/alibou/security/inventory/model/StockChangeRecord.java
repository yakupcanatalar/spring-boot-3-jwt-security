package com.alibou.security.inventory.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stocks_change_records")
public class StockChangeRecord {

    @Id
    private Long id;

    private long userId;

    private String barcode;

    private int qty;

    private long oldQuantity;

    private long currentQuantity;

    @Enumerated
    private StockChangeType changeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(long oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public long getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public StockChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(StockChangeType changeType) {
        this.changeType = changeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockChangeRecord that = (StockChangeRecord) o;
        return userId == that.userId && qty == that.qty && oldQuantity == that.oldQuantity && currentQuantity == that.currentQuantity && Objects.equals(id, that.id) && Objects.equals(barcode, that.barcode) && changeType == that.changeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, barcode, qty, oldQuantity, currentQuantity, changeType);
    }
}
