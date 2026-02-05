package com.shopsmart.user_service.service;

import com.shopsmart.user_service.dto.UserRequest;
import com.shopsmart.user_service.model.User;
import com.shopsmart.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        // Password ko encrypt karke save kar rahe hain (Security ke liye)
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        user.setRole("USER"); // By default sab USER honge

        userRepository.save(user); // Database mein save ho gaya!
        return "User Registered Successfully!";
    }
}