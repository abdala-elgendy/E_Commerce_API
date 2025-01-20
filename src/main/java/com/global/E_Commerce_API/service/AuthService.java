package com.global.E_Commerce_API.service;

import com.global.E_Commerce_API.dto.LoginRequest;
import com.global.E_Commerce_API.dto.RegisterRequest;
import com.global.E_Commerce_API.model.Role;
import com.global.E_Commerce_API.model.User;
import com.global.E_Commerce_API.repository.UserRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
       // user.setId(43L);
        user.setRole("ROLE_USER");
        if(userRepo.findUserByEmail(request.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        try {
            userRepo.save(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e);

        }
    }

    public ResponseEntity<?> loginUser(LoginRequest request) {
        User user = userRepo.findUserByEmail(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials!");
            }
        } else {
            return ResponseEntity.badRequest().body(request.
                    getEmail()+" not found!");

        }
    }



}