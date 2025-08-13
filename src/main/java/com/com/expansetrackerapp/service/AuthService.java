package com.expansetrackerapp.service;

import com.expansetrackerapp.dto.*;

public interface AuthService {
    com.expansetrackerapp.dto.AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
}