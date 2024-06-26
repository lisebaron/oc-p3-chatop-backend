package com.chatopbackend.chatopbackend.repository;

import com.chatopbackend.chatopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
