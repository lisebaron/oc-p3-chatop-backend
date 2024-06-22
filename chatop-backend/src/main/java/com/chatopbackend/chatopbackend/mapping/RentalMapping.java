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
     * Maps a Rental entity to a RentalDto.
     *
     * @param rental the Rental entity to be mapped.
     * @return RentalDto containing the mapped details of the Rental entity.
     */
    public RentalDto mapRentalToRentalDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture("/api/" + dirName + "/" + rental.getId() + "/" + rental.getPicture())
                .description(rental.getDescription())
                .owner(rental.getOwner().getId())
                .createdAt(DateUtils.convertDateToString(rental.getCreatedAt()))
                .updatedAt(DateUtils.convertDateToString(rental.getUpdatedAt()))
                .build();
    }

    /**
     * Converts a list of Rental entities to a RentalListResponse containing DTOs.
     *
     * @param rentals the list of Rental entities to be converted.
     * @return RentalListResponse containing DTOs mapped from Rental entities.
     */
    public RentalListResponse convertListToDto(List<Rental> rentals) {
        RentalListResponse rentalListResponse = new RentalListResponse();
        rentalListResponse.setRentals(rentals.stream().map(this::mapRentalToRentalDto).collect(Collectors.toList()));
        return rentalListResponse;
    }
}
