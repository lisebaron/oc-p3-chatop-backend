package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.service.RentalService;
import com.chatopbackend.chatopbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasRole('USER')")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;

    public RentalController(RentalService rentalService, UserService userService) {
        this.rentalService = rentalService;
        this.userService = userService;
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
        return new RentalDto(rental);
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
        if (!Objects.equals(request.getSurface(), rental.getSurface())) {
            rental.setSurface(request.getSurface());
        }
        if (!Objects.equals(request.getPrice(), rental.getPrice())) {
            rental.setPrice(request.getPrice());
        }
        if (!request.getDescription().isEmpty()) {
            rental.setDescription(request.getDescription());
        }
        // update rental
        return new RentalDto(rental);
    }

    @PostMapping("/api/rentals")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDto createRental(@RequestBody Rental request) {
        User owner = userService.getUserById(1); // TODO changer le 1 par owner_id
        Rental createdRental = rentalService.createRental(request.getName(), request.getSurface(), request.getPrice(), request.getPicture(), request.getDescription(), owner);
        return new RentalDto(createdRental);
    }

    private List<RentalDto> convertListToDto(List<Rental> rentals) {
        return rentals.stream().map(r -> {
            RentalDto rentalDto = new RentalDto(r);
            return rentalDto;
        }).collect(Collectors.toList());
    }
}
