package com.blog.blog.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticateUser(String username, String password);
    String generateToken(UserDetails userDetails);
}
