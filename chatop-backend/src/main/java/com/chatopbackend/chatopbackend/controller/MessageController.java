package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.MessageDto;
import com.chatopbackend.chatopbackend.model.Message;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.MessageService;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final RentalService rentalService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public MessageController(MessageService messageService, RentalService rentalService, UserService userService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.rentalService = rentalService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto createMessage(@RequestBody MessageDto messageDto) throws ParseException {
        User user = userService.getUserById(messageDto.getUserId());
        Rental rental = rentalService.getRentalById(messageDto.getRentalId());
        Message message = convertToEntity(messageDto.getMessage(), user, rental);

        Message messageCreated = messageService.createMessage(rental, user, message.getMessage());
        messageDto.setId(messageCreated.getId());
        return messageDto;
//        return convertToDto(messageCreated);
    }

//    private MessageDto convertToDto(Message message) {
//        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
//        return messageDto;
//    }

    private Message convertToEntity(String messageStr, User user, Rental rental) throws ParseException {
        Message message = new Message(rental, user, messageStr);
        return message;
    }
}
