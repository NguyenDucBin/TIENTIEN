package com.example.Evara_Shop.validation;

import com.example.Evara_Shop.DTO.user.UserCreateDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("userValidator")
public class UserValidator implements ValidatorStrategy<UserCreateDTO> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z]).{6,18}$");

    @Override
    public void validate(UserCreateDTO dto) {
        if(dto.getEmail() == null || dto.getName() == null || dto.getPassword() == null){
            throw new IllegalArgumentException("Email or name or password cannot be null");
        }
        if(!EMAIL_PATTERN.matcher(dto.getEmail()).matches()){
            throw new IllegalArgumentException("Invalid email");
        }
        if (!PASSWORD_PATTERN.matcher(dto.getPassword()).matches()){
            throw new IllegalArgumentException("Invalid password");
        }
    }
}
