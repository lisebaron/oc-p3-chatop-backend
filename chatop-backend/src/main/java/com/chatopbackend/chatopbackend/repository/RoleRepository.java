package com.chatopbackend.chatopbackend.repository;

import java.util.Optional;

import com.chatopbackend.chatopbackend.model.ERole;
import com.chatopbackend.chatopbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}