package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.RentalRepository;
import com.chatopbackend.chatopbackend.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    public Rental getRentalById(Integer id) {
        /**
         * utiliser le style Exception Business comme UserNotFoundException
         * if (rental == null) {
         *     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found with ID: " + id);
         * }
         */
        return rentalRepository.findById(id)
                .orElse(null);
    }

    public Rental createRental(String name, float surface, float price, String picture, String description, User user) {
        Rental rental = new Rental(name, surface, price, picture, description, user);
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Integer id, RentalDto updatedRentalDto) {
        Rental existingRental = getRentalById(id);

        if (Utils.isStringNotEmpty(updatedRentalDto.getName())) {
            existingRental.setName(updatedRentalDto.getName());
        }
        if (!Objects.equals(updatedRentalDto.getSurface(), existingRental.getSurface())) {
            existingRental.setSurface(updatedRentalDto.getSurface());
        }
        if (!Objects.equals(updatedRentalDto.getPrice(), existingRental.getPrice())) {
            existingRental.setPrice(updatedRentalDto.getPrice());
        }
        if (Utils.isStringNotEmpty(updatedRentalDto.getDescription())) {
            existingRental.setDescription(updatedRentalDto.getDescription());
        }

        return rentalRepository.save(existingRental);
    }
}
