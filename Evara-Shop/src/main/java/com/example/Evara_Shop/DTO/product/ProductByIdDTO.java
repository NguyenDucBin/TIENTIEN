package com.example.Evara_Shop.DTO.product;

import com.example.Evara_Shop.model.Product;

import java.math.BigDecimal;

public class ProductByIdDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Long supplierId;

    public ProductByIdDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.oldPrice = product.getOldPrice();
        this.supplierId = product.getSupplier().getId();
    }

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
