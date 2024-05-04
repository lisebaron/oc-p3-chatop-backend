package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.model.Message;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }


    public Message createMessage(Rental rental, User owner, String message) {
        Message createdMessage = new Message(rental, owner, message);
        return messageRepository.save(createdMessage);
    }
}
