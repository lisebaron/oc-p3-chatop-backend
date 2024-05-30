package com.chatopbackend.chatopbackend.dto;

import com.chatopbackend.chatopbackend.model.Rental;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalDto {

    private Integer id;
    private String name;
    private Float surface;
    private Float price;
    private String picture;
    private String description;
    private Integer owner_id;
    private String created_at;
    private String updated_at;

    public RentalDto(Rental rental) {
        this.id = rental.getId();
        this.name = rental.getName();
        this.surface = rental.getSurface();
        this.price = rental.getPrice();
        this.picture = rental.getPicture();
        this.description = rental.getDescription();
        this.owner_id = rental.getOwner().getId();
        this.created_at = convertDateToString(rental.getCreatedAt());
        this.updated_at = convertDateToString(rental.getUpdatedAt());
    }

    public RentalDto() {
    }

    private String convertDateToString (Date date) {
        String pattern = "yyyy/dd/MM";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSurface() {
        return surface;
    }

    public void setSurface(Float surface) {
        this.surface = surface;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
