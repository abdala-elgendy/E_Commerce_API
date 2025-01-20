package com.global.E_Commerce_API.controller;

import com.global.E_Commerce_API.dto.LoginRequest;
import com.global.E_Commerce_API.dto.RegisterRequest;
import com.global.E_Commerce_API.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        //return ResponseEntity.ok("User registered successfully!");
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
    return authService.loginUser(request);
    }
    }
