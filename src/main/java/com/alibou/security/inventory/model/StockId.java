package com.alibou.security.inventory.model;

import java.io.Serializable;
import java.util.Objects;

public class StockId implements Serializable {

    private long userId;

    private String barcode;

    public StockId(long userId, String barcode) {
        this.userId = userId;
        this.barcode = barcode;
    }

    public StockId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return userId == stockId.userId && Objects.equals(barcode, stockId.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, barcode);
    }
}
