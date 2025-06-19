package com.example.Evara_Shop.validation.user;

import com.example.Evara_Shop.DTO.user.UserUpdateDTO;
import com.example.Evara_Shop.utils.UserValidationUtils;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.stereotype.Component;

@Component("userUpdateValidator")
public class UserUpdateValidator implements ValidatorStrategy<UserUpdateDTO> {

    @Override
    public void validate(UserUpdateDTO dto) {
        UserValidationUtils.validateEmail(dto.getEmail());
        UserValidationUtils.validatePassword(dto.getPassword());
    }
}
