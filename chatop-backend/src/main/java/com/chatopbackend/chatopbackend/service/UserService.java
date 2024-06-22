package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.exception.EmailAlreadyInUseException;
import com.chatopbackend.chatopbackend.exception.UserNotFoundException;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the retrieved User object
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email address of the user to retrieve
     * @return an Optional containing the retrieved User object, or an empty Optional if not found
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    /**
     * Creates a new user entity and saves it.
     *
     * @param email the email address of the user
     * @param name the name of the user
     * @param password the password of the user
     * @return the created User object
     */
    public User createUser(String email, String name, String password) {
        try {
            User user = new User(email, name, password);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException("Email address already in use");
        }
    }
}
