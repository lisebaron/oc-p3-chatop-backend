package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.exception.RentalNotFoundException;
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

    /**
     * Retrieves all rentals.
     *
     * @return the list of all rentals
     */
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }


    /**
     * Retrieves a rental by its ID.
     *
     * @param id the ID of the rental to retrieve
     * @return the retrieved rental object
     * @throws RentalNotFoundException if the rental with the specified ID is not found
     */
    public Rental getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found"));
    }


    /**
     * Creates a new rental entity and saves it.
     *
     * @param name the name of the rental
     * @param surface the surface area of the rental
     * @param price the price of the rental
     * @param picture the picture name of the rental
     * @param description the description of the rental
     * @param user the user who owns the rental
     * @return the created Rental object
     */
    public Rental createRental(String name, float surface, float price, String picture, String description, User user) {
        Rental rental = new Rental(name, surface, price, picture, description, user);
        return rentalRepository.save(rental);
    }

    /**
     * Updates an existing rental entity based on the provided ID and updated data.
     *
     * @param id the ID of the rental we want to update
     * @param updatedRentalDto the DTO containing updated rental information
     * @return the updated Rental object
     */
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
