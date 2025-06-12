package com.example.Evara_Shop.controller;

import com.example.Evara_Shop.DTO.user.LoginRequestDTO;
import com.example.Evara_Shop.DTO.user.LoginResponseDTO;
import com.example.Evara_Shop.DTO.user.UserCreateDTO;
import com.example.Evara_Shop.DTO.user.UserDTO;
import com.example.Evara_Shop.model.User;
import com.example.Evara_Shop.repository.UserRepo;
import com.example.Evara_Shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findByRole() {
        return ResponseEntity.ok(userService.findByRole());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.create(userCreateDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
