package com.chatopbackend.chatopbackend.mapping;

import com.chatopbackend.chatopbackend.dto.RentalDto;
import com.chatopbackend.chatopbackend.model.Rental;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RentalMapping {
    @Value("${file.upload.dir}")
    private String dirName;

    public RentalDto mapRentalToRentalDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setPicture("/api/" + dirName + "/" + rental.getId() + "/" + rental.getPicture());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setOwner_id(rental.getOwner().getId());
        rentalDto.setCreated_at(convertDateToString(rental.getCreatedAt()));
        rentalDto.setUpdated_at(convertDateToString(rental.getUpdatedAt()));
        return rentalDto;
    }

    //TODO a mettre dans un utils
    private String convertDateToString (Date date) {
        String pattern = "yyyy/dd/MM";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
