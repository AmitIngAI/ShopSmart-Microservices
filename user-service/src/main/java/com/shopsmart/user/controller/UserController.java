package com.shopsmart.user.controller;

import com.shopsmart.common.dto.UserDTO;
import com.shopsmart.user.model.User;
import com.shopsmart.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDTO(u.getId(), u.getEmail(), u.getName(), u.getRole()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(u -> ResponseEntity.ok(new UserDTO(u.getId(), u.getEmail(), u.getName(), u.getRole())))
                .orElse(ResponseEntity.notFound().build());
    }
}