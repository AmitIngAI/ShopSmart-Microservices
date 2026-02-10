package com.shopsmart.user.dto;

import com.shopsmart.common.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private UserDTO user;
    private String token;
}