package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> register(@RequestBody User request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        User createdUser = userService.createUser(request.getEmail(), request.getName(), encryptedPassword);
        return ResponseEntity.created(URI.create("/api/auth/register")).body(createdUser);
    }
}
