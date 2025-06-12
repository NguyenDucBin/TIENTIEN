package com.example.Evara_Shop.builder;

import com.example.Evara_Shop.DTO.user.UserCreateDTO;
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
}
