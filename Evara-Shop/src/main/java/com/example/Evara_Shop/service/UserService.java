package com.example.Evara_Shop.service;

import com.example.Evara_Shop.DTO.user.LoginRequestDTO;
import com.example.Evara_Shop.DTO.user.LoginResponseDTO;
import com.example.Evara_Shop.DTO.user.UserCreateDTO;
import com.example.Evara_Shop.DTO.user.UserDTO;
import com.example.Evara_Shop.builder.UserBuilder;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired @Qualifier("userValidator")
    UserValidator userValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findByRole() {
        List<User> users = userRepo.findByRole("customer");
        return SimpleServiceHelper.mapToDTOList(users, UserDTO::new);
    }

    public UserDTO create(UserCreateDTO dto) {
        userValidator.validate(dto);
        User user = UserBuilder.from(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return SimpleServiceHelper.saveAndReturnDTO(user, userRepo, UserDTO::new);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng.");
        }

        return new LoginResponseDTO(user);
    }
}
