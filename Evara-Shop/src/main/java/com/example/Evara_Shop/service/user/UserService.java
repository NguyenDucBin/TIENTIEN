package com.example.Evara_Shop.service.user;

import com.example.Evara_Shop.DTO.user.*;
import com.example.Evara_Shop.builder.UserBuilder;
import com.example.Evara_Shop.chain.user.RoleCheckHandler;
import com.example.Evara_Shop.command.UpdateUserCommand;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.observer.UserRegistrationPublisher;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.service.SimpleServiceHelper;
import com.example.Evara_Shop.validation.user.UserCreateValidator;
import com.example.Evara_Shop.validation.user.UserUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private RoleCheckHandler roleCheck;

    @Autowired @Qualifier("userCreateValidator")
    UserCreateValidator userCreateValidator;
    @Autowired @Qualifier("userUpdateValidator")
    UserUpdateValidator userUpdateValidator;

    @Autowired
    UserRegistrationPublisher userRegistrationPublisher;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findByRole() {
        List<User> users = userRepo.findByRole("customer");
        return SimpleServiceHelper.mapToDTOList(users, UserDTO::new);
    }

    public UserDTO create(UserCreateDTO dto) {
        userCreateValidator.validate(dto);
        User user = UserBuilder.from(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepo.save(user);
        userRegistrationPublisher.notifyAll(saved);

        return new UserDTO(saved);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng.");
        }

        return new LoginResponseDTO(user);
    }

    public UserDTO update(Long id, UserUpdateDTO dto) {
        UpdateUserCommand cmd = new UpdateUserCommand(id, dto, userRepo, passwordEncoder, userUpdateValidator);
        return cmd.execute();
    }

    public void delete(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // Ghép chuỗi kiểm tra
        roleCheck.handle(user); // chạy qua chain

        userRepo.delete(user); // chỉ xóa nếu qua toàn bộ handler
    }
}
