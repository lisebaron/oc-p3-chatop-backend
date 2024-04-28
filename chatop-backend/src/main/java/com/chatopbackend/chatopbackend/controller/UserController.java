package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> register(@RequestBody User request) {
        User createdUser = userService.createUser(request.getEmail(), request.getName(), request.getPassword());
        return ResponseEntity.created(URI.create("/api/auth/register")).body(createdUser);
    }
}
