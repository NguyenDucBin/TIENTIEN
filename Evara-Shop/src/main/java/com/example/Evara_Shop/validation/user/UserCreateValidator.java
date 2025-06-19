package com.example.Evara_Shop.validation.user;

import com.example.Evara_Shop.DTO.user.UserCreateDTO;
import com.example.Evara_Shop.utils.UserValidationUtils;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

@Component("userCreateValidator")
public class UserCreateValidator implements ValidatorStrategy<UserCreateDTO> {

    @Override
    public void validate(UserCreateDTO dto) {
        UserValidationUtils.validateEmail(dto.getEmail());
        UserValidationUtils.validatePassword(dto.getPassword());
    }
}
