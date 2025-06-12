package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.model.Product;
import com.example.Evara_Shop.model.Supplier;

public class ProductBuilder {
    public static Product from(ProductCreateDTO dto, Supplier supplier) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setOldPrice(dto.getOldPrice());
        p.setImage(dto.getImage());
        p.setImageHover(dto.getImageHover());
        p.setStock(dto.getStock());
        p.setSupplier(supplier);
        return p;
    }
}


