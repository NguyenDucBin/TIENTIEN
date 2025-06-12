package com.example.Evara_Shop.DTO.user;

import com.example.Evara_Shop.model.User;

public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    public LoginResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}

