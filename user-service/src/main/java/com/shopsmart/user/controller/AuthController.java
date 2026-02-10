package com.shopsmart.user.controller;

import com.shopsmart.common.dto.UserDTO;
import com.shopsmart.common.utils.JwtUtil;
import com.shopsmart.user.dto.AuthResponse;
import com.shopsmart.user.dto.LoginRequest;
import com.shopsmart.user.dto.RegisterRequest;
import com.shopsmart.user.model.User;
import com.shopsmart.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("CUSTOMER");
        
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getRole());
        
        return ResponseEntity.ok(new AuthResponse(userDTO, token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getRole());
        
        return ResponseEntity.ok(new AuthResponse(userDTO, token));
    }
}