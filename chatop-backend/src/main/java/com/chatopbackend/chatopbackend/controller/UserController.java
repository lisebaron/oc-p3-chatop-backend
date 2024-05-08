package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody UserDto userDto) {
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        User createdUser = userService.createUser(userDto.getEmail(), userDto.getName(), encryptedPassword);
        return new UserDto(createdUser);
    }
}
