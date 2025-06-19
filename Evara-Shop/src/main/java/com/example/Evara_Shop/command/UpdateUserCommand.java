package com.example.Evara_Shop.command;

import com.example.Evara_Shop.DTO.user.UserDTO;
import com.example.Evara_Shop.DTO.user.UserUpdateDTO;
import com.example.Evara_Shop.builder.UserBuilder;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.validation.ValidatorStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserCommand implements UpdateCommand<UserDTO> {

    private final Long id;
    private final UserUpdateDTO dto;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final ValidatorStrategy<UserUpdateDTO> validator;

    public UpdateUserCommand(Long id, UserUpdateDTO dto,
                             UserRepo userRepo,
                             PasswordEncoder encoder,
                             ValidatorStrategy<UserUpdateDTO> validator) {
        this.id = id;
        this.dto = dto;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.validator = validator;
    }

    @Override
    public UserDTO execute() {
        validator.validate(dto);
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        UserBuilder.updateFromDTO(user, dto, encoder);
        return new UserDTO(userRepo.save(user));
    }
}

