package com.chatopbackend.chatopbackend.controller;

import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/api/rentals")
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok().body(rentals);
    }

    @GetMapping("/api/rentals/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Integer id) {
        Optional<Rental> optRental = rentalService.getRentalById(id);
        if (optRental.isPresent()) {
            return ResponseEntity.ok().body(optRental.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/rentals/{id}")
    public ResponseEntity<Rental> editRentalById(@PathVariable Integer id, @RequestBody Rental request) {
        Optional<Rental> optRental = rentalService.getRentalById(id);
        if (optRental.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Rental rental = optRental.get();
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
        return ResponseEntity.ok().body(rental);
    }

    @PostMapping("/api/rentals")
    public ResponseEntity<Rental> createRental(@RequestBody Rental request) {
        Rental createdRental = rentalService.createRental(request.getName(), request.getSurface(), request.getPrice(), request.getPicture(), request.getDescription());
        return ResponseEntity.created(URI.create("/api/rentals")).body(createdRental);
    }
}
