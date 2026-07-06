package com.cihangunhan.cinemabookingservice.controller;

import com.cihangunhan.cinemabookingservice.dto.AuthResponse;
import com.cihangunhan.cinemabookingservice.dto.LoginRequest;
import com.cihangunhan.cinemabookingservice.dto.RegisterRequest;
import com.cihangunhan.cinemabookingservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(
                        request.getFullName(),
                        request.getEmail(),
                        request.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(
                authService.login(
                        request.getEmail(),
                        request.getPassword()));
    }
}