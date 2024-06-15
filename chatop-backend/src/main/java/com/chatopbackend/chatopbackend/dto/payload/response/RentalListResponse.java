package com.chatopbackend.chatopbackend.dto.payload.response;

import com.chatopbackend.chatopbackend.dto.RentalDto;

import java.util.ArrayList;
import java.util.List;

public class RentalListResponse {
  private List<RentalDto> rentals = new ArrayList<>();

  public List<RentalDto> getRentals() {
        return rentals;
    }
  public void setRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
    }
}