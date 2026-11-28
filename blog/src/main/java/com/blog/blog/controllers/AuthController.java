package com.blog.blog.controllers;

import com.blog.blog.domain.dtos.AuthResponse;
import com.blog.blog.domain.dtos.LoginRequest;
import com.blog.blog.domain.dtos.RegisterRequestDto;
import com.blog.blog.services.AuthenticationService;
import com.blog.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

@PostMapping("/login")
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
@PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid
                                                 RegisterRequestDto registerRequestDto){
    userService.createUser(registerRequestDto);
    UserDetails userDetails = authenticationService.authenticateUser(
            registerRequestDto.getEmail(),
            registerRequestDto.getPassword());
    String accessToken = authenticationService.generateToken(userDetails);
    AuthResponse authResponse =
            AuthResponse
                    .builder().
                    accessToken(accessToken).expiresIn(86400).build();
    return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
}
}
