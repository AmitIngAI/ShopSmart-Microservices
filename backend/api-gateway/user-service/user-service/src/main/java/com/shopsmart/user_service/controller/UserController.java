package com.shopsmart.user_service.controller;

import com.shopsmart.user_service.dto.UserRequest;
import com.shopsmart.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Naya user banane ke liye
    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    // Check karne ke liye ki service chal rahi hai ya nahi
    @GetMapping("/test")
    public String test() {
        return "User Service is Working!";
    }
}