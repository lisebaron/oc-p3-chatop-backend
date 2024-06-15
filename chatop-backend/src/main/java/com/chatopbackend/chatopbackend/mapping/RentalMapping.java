package com.chatopbackend.chatopbackend.mapping;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.model.Rental;
import com.chatopbackend.chatopbackend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RentalMapping {
    @Value("${file.upload.dir}")
    private String dirName;

    //faire un builder
    public RentalDto mapRentalToRentalDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setPicture("/api/" + dirName + "/" + rental.getId() + "/" + rental.getPicture());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setOwner_id(rental.getOwner().getId());
        rentalDto.setCreated_at(DateUtils.convertDateToString(rental.getCreatedAt()));
        rentalDto.setUpdated_at(DateUtils.convertDateToString(rental.getUpdatedAt()));
        return rentalDto;
    }
}
