package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.MessageDto;
import com.chatopbackend.chatopbackend.model.Message;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.MessageService;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@PreAuthorize("hasRole('USER')")
public class MessageController {

    private final MessageService messageService;
    private final RentalService rentalService;
    private final UserService userService;

    public MessageController(MessageService messageService, RentalService rentalService, UserService userService) {
        this.messageService = messageService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

    @PostMapping("/api/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto createMessage(@RequestBody MessageDto messageDto) throws ParseException {
        User user = userService.getUserById(messageDto.getUser_id());
        Rental rental = rentalService.getRentalById(messageDto.getRental_id());

        Message messageCreated = messageService.createMessage(rental, user, messageDto.getMessage());
        return new MessageDto(messageCreated);
    }
}
