package com.chatopbackend.chatopbackend.repository;

import com.chatopbackend.chatopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
