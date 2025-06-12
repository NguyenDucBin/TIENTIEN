package com.example.Evara_Shop.DTO.product;

import java.math.BigDecimal;
import com.example.Evara_Shop.model.Product;

public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String supplierName;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.supplierName = product.getSupplier() != null ? product.getSupplier().getName() : null;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getSupplierName() { return supplierName; }
}
