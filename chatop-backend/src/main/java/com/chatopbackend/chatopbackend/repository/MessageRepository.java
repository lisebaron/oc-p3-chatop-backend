package com.chatopbackend.chatopbackend.repository;

import com.chatopbackend.chatopbackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
