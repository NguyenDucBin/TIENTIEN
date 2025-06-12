package com.example.Evara_Shop.DTO.product;

import java.math.BigDecimal;

public class ProductCreateDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private String image;
    private String imageHover;
    private int stock;
    private Long supplierId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageHover() {
        return imageHover;
    }

    public void setImageHover(String imageHover) {
        this.imageHover = imageHover;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    // Getters and setters
}

