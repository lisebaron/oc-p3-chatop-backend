package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.MessageDto;
import com.chatopbackend.chatopbackend.dto.payload.response.MessageResponse;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.MessageService;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;
    private final RentalService rentalService;
    private final UserService userService;

    public MessageController(MessageService messageService, RentalService rentalService, UserService userService) {
        this.messageService = messageService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

    /**
     * Creates a new message based on the provided MessageDto.
     *
     * @param messageDto DTO for message, contains message details
     * @return ResponseEntity indicating successful creation of the message
     * @throws ParseException if there is an error while parsing data in the messageDto
     */
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(final @RequestBody MessageDto messageDto) throws ParseException {
        final User user = userService.getUserById(messageDto.getUser_id());
        final Rental rental = rentalService.getRentalById(messageDto.getRental_id());
        messageService.createMessage(rental, user, messageDto.getMessage());
        return ResponseEntity.ok(new MessageResponse("Message send with success"));
    }
}
