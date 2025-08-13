package com.expansetrackerapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expansetrackerapp.dto.AuthRequest;
import com.expansetrackerapp.dto.AuthResponse;
import com.expansetrackerapp.dto.RegisterRequest;
import com.expansetrackerapp.entity.User;
import com.expansetrackerapp.repository.*;
import com.expansetrackerapp.dto.*;
import com.expansetrackerapp.security.JwtUtil;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        var token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

}
