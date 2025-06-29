package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.DTO.product.ProductUpdateDTO;
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

    public static void updateFromDTO(Product product, ProductUpdateDTO dto, Supplier supplier) {
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());
        product.setOldPrice(dto.getOldPrice());
        if (supplier != null) product.setSupplier(supplier);
    }
}


