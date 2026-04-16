package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
}