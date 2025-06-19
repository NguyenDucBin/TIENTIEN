package com.example.Evara_Shop.DTO.product;

import java.math.BigDecimal;

public class ProductUpdateDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Long supplierId;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }
}

