package com.shopsmart.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                   // Ye batata hai ki ye Database ki table hai
@Table(name = "users")    // Table ka naam 'users' hoga
@Data                     // Lombok: Getters/Setters auto-generate karega
@AllArgsConstructor       // Constructor with args
@NoArgsConstructor        // Empty constructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      // Unique ID (1, 2, 3...)

    private String name;

    @Column(unique = true) // Email duplicate nahi ho sakta
    private String email;

    private String password;

    private String role; // Role: USER ya ADMIN
}