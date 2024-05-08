package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.exception.EmailAlreadyInUseException;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(String email, String name, String password) {
        try {
            User user = new User(email, name, password);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException("Email address already in use");
        }
    }
}
