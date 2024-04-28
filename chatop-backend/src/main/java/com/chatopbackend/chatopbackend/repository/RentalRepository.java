package com.chatopbackend.chatopbackend.repository;

import com.chatopbackend.chatopbackend.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
