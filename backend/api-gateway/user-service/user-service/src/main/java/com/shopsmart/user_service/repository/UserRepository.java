package com.shopsmart.user_service.repository;

import com.shopsmart.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository humein bana-banaya code deta hai (save, findById etc.)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Email se user dhundne ke liye
}