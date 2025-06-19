package com.example.Evara_Shop.validation.product;

import com.example.Evara_Shop.DTO.product.ProductUpdateDTO;
import com.example.Evara_Shop.utils.ProductValidationUtils;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

@Component("productUpdateValidator")
public class ProductUpdateValidator implements ValidatorStrategy<ProductUpdateDTO> {
    @Override
    public void validate(ProductUpdateDTO dto) {
        ProductValidationUtils.validatePrice(dto.getPrice());
        ProductValidationUtils.validateOldPrice(dto.getOldPrice());
        ProductValidationUtils.validateNewPrice(dto.getOldPrice(), dto.getPrice());
    }
}
