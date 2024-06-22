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

    /**
     * Creates a new message associated with a rental.
     *
     * @param rental the rental object to which the message is associated
     * @param owner the user who owns the message
     * @param message the content of the message
     * @return the created Message object saved in the repository
     */
    public Message createMessage(Rental rental, User owner, String message) {
        Message createdMessage = new Message(rental, owner, message);
        return messageRepository.save(createdMessage);
    }
}
