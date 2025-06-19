package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.user.UserCreateDTO;
import com.example.Evara_Shop.DTO.user.UserUpdateDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.Evara_Shop.model.User;

import java.time.LocalDate;

public class UserBuilder {
    public static User from(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setRole("customer");
        user.setCreatedAt(LocalDate.now().atStartOfDay());
        return user;
    }

    public static void updateFromDTO(User user, UserUpdateDTO dto, PasswordEncoder encoder) {
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getRole() != null) user.setRole(dto.getRole());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(encoder.encode(dto.getPassword()));
        }
    }
}
