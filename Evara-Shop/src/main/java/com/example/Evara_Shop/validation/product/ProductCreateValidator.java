package com.example.Evara_Shop.validation.product;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.utils.ProductValidationUtils;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("productCreateValidator")
public class ProductCreateValidator implements ValidatorStrategy<ProductCreateDTO> {
    @Override
    public void validate(ProductCreateDTO dto) {
        if(dto.getName() == null || dto.getPrice() == null) {
            throw new IllegalArgumentException("Invalid product");
        }
        ProductValidationUtils.validateNewPrice(dto.getOldPrice(), dto.getPrice());
        ProductValidationUtils.validateOldPrice(dto.getOldPrice());
        ProductValidationUtils.validatePrice(dto.getPrice());
    }
}


