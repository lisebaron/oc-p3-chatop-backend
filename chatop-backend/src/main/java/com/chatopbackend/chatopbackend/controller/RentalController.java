package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RentalController(RentalService rentalService, UserService userService, ModelMapper modelMapper) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/rentals")
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return convertListToDto(rentals);
    }

    @GetMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto getRentalById(@PathVariable Integer id) {
        Rental rental = rentalService.getRentalById(id);
        return convertToDto(rental);
    }

    @PutMapping("/api/rentals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto editRentalById(@PathVariable Integer id, @RequestBody Rental request) {
        Rental rental = rentalService.getRentalById(id);
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found with ID: " + id);
        }

        if (!request.getName().isEmpty()) {
            rental.setName(request.getName());
        }
        if (request.getSurface() != rental.getSurface()) {
            rental.setSurface(request.getSurface());
        }
        if (request.getPrice() != rental.getPrice()) {
            rental.setPrice(request.getPrice());
        }
        if (!request.getDescription().isEmpty()) {
            rental.setDescription(request.getDescription());
        }
        // update rental
        return convertToDto(rental);
    }

    @PostMapping("/api/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDto createRental(@RequestBody Rental request) {
        User owner = userService.getUserById(1); // TODO changer le 1 par owner_id
        Rental createdRental = rentalService.createRental(request.getName(), request.getSurface(), request.getPrice(), request.getPicture(), request.getDescription(), owner);
        return convertToDto(createdRental);
    }

    private RentalDto convertToDto(Rental rental) {
        return modelMapper.map(rental, RentalDto.class);
    }

    private List<RentalDto> convertListToDto(List<Rental> rentals) {
        return rentals.stream().map(r -> {
            RentalDto rentalDto = new RentalDto();
            rentalDto.setId(r.getId());
            rentalDto.setName(r.getName());
            rentalDto.setSurface(r.getSurface());
            rentalDto.setPrice(r.getPrice());
            rentalDto.setPicture(r.getPicture());
            rentalDto.setDescription(r.getDescription());
            rentalDto.setOwnerId(r.getOwnerId().getId());
            Date date = new Date(r.getCreatedAt().getTime());
            rentalDto.setCreatedAt(convertDateToString(date));
            return rentalDto;
        }).collect(Collectors.toList());
    }

    private String convertDateToString (Date date) {
        String pattern = "yyyy/dd/MM";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

//    private Rental convertToEntity(RentalDto rentalDto) throws ParseException {
//        return modelMapper.map(rentalDto, Rental.class);
//    }
}
