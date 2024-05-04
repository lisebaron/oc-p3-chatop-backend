package com.chatopbackend.chatopbackend.service;

import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.model.User;
import com.chatopbackend.chatopbackend.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
//    public Optional<Rental> getRentalById(Integer id) {
//        return rentalRepository.findById(id);
//    }
    public Rental getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .orElse(null);
    }

    public Rental createRental(String name, float surface, float price, String picture, String description, User user) {
        Rental rental = new Rental(name, surface, price, picture, description, user);
        return rentalRepository.save(rental);
    }

//    public Rental updateRental(Integer id, Rental updatedRental) {
//        Optional<Rental> optRental = rentalRepository.findById(id);
//        Rental rental = optRental.get();
//        rentalRepository.save()
//    }
}
