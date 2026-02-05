package com.shopsmart.user_service.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
}