package com.blog.blog.services.impl;

import com.blog.blog.domain.dtos.RegisterRequestDto;
import com.blog.blog.domain.entities.User;
import com.blog.blog.domain.repositories.UserRepository;
import com.blog.blog.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(RegisterRequestDto registerRequestDto) {

        if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()){
            throw  new IllegalArgumentException("This Email is already exist");
        }

        User user = User.builder()
                .name(registerRequestDto.getName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .build();
        return userRepository.save(user);
    }
}
