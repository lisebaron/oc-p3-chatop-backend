package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.model.Message;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.service.MessageService;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final RentalService rentalService;
    private final UserService userService;

    public MessageController(MessageService messageService, RentalService rentalService, UserService userService) {
        this.messageService = messageService;
        this.rentalService = rentalService;
        this.userService = userService;
    }

//    @PostMapping("/api/messages")
//    public ResponseEntity<Message> createMessage(@RequestBody Message request) {
//        Rental rental = rentalService.getRentalById()
//        Message createdMessage = messageService.createMessage(request.getRental_id(),1, request.getMessage() );
//        return ResponseEntity.created(URI.create("/api/messages")).body(createdMessage);
//    }
}
