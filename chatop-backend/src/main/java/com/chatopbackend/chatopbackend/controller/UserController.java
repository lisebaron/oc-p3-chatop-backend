package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.UserDto;
import com.chatopbackend.chatopbackend.mapping.UserMapping;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapping userMapping;

    public UserController(UserService userService, UserMapping userMapping) {
        this.userService = userService;
        this.userMapping = userMapping;
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return userMapping.mapUserToUserDto(user);
    }
}
