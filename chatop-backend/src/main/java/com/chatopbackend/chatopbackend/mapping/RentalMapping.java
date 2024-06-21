package com.chatopbackend.chatopbackend.mapping;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.dto.payload.response.RentalListResponse;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapping {
    @Value("${file.upload.dir}")
    private String dirName;

    /**
     * Maps a Rental to a RentalDto
     * @param rental
     * @return RentalDto
     */
    public RentalDto mapRentalToRentalDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture("/api/" + dirName + "/" + rental.getId() + "/" + rental.getPicture())
                .description(rental.getDescription())
                .createdAt(DateUtils.convertDateToString(rental.getCreatedAt()))
                .updatedAt(DateUtils.convertDateToString(rental.getUpdatedAt()))
                .build();
    }

    /**
     * Converts a list of Rentals into a list of RentalDtos
     * @param rentals
     * @return RentalListResponse
     */
    public RentalListResponse convertListToDto(List<Rental> rentals) {
        RentalListResponse rentalListResponse = new RentalListResponse();
        rentalListResponse.setRentals(rentals.stream().map(this::mapRentalToRentalDto).collect(Collectors.toList()));
        return rentalListResponse;
    }
}
