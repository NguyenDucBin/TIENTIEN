package com.example.Evara_Shop.validation;

import com.example.Evara_Shop.DTO.supplier.SupplierCreateDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("supplierValidator")
public class SupplierValidator implements ValidatorStrategy<SupplierCreateDTO>{

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    @Override
    public void validate(SupplierCreateDTO dto) {
        if(dto.getEmail() == null || dto.getAddress() == null || dto.getName() == null || dto.getPhone() == null){
            throw new IllegalArgumentException("Supplier email and address are required");
        }
        if(!EMAIL_PATTERN.matcher(dto.getEmail()).matches()){
            throw new IllegalArgumentException("Supplier email is invalid");
        }
    }
}
