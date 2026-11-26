package com.blog.blog.controllers;

import com.blog.blog.domain.dtos.AuthResponse;
import com.blog.blog.domain.dtos.LoginRequest;
import com.blog.blog.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
@PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
    UserDetails userDetails = authenticationService.authenticateUser(
            loginRequest.getEmail(),
            loginRequest.getPassword());
    String accessToken = authenticationService.generateToken(userDetails);
    AuthResponse authResponse =
            AuthResponse
                    .builder().
                    accessToken(accessToken).expiresIn(86400).build();
    return  ResponseEntity.ok(authResponse);
}
}
