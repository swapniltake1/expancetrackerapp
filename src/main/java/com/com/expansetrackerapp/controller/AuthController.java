package com.expansetrackerapp.controller;

import com.expansetrackerapp.dto.AuthRequest;
import com.expansetrackerapp.dto.AuthResponse;
import com.expansetrackerapp.dto.RegisterRequest;
// Ensure the AuthService class exists at this path, or update the import to the correct package
import com.expansetrackerapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}