package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String name, String password) {
        User user = new User(email, name, password);
        return userRepository.save(user);
    }
}
