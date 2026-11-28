package com.blog.blog.services;

import com.blog.blog.domain.dtos.RegisterRequestDto;
import com.blog.blog.domain.entities.User;

public interface UserService {
    User createUser(RegisterRequestDto registerRequestDto);
}
