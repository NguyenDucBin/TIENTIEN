package com.example.Evara_Shop.validation;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("productValidator")
public class ProductValidator implements ValidatorStrategy<ProductCreateDTO>{
    @Override
    public void validate(ProductCreateDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank())
            throw new IllegalArgumentException("Tên không được để trống");
        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
    }
}


