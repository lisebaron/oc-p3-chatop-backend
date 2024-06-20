package com.chatopbackend.chatopbackend.dto;

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

    public RentalDto() {
    }
    private RentalDto(RentalBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surface = builder.surface;
        this.price = builder.price;
        this.picture = builder.picture;
        this.description = builder.description;
        this.owner_id = builder.owner_id;
        this.created_at = builder.created_at;
        this.updated_at = builder.updated_at;
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

    public static RentalBuilder builder() {
        return new RentalBuilder();
    }

    public static class RentalBuilder {
        private Integer id;
        private String name;
        private Float surface;
        private Float price;
        private String picture;
        private String description;
        private Integer owner_id;
        private String created_at;
        private String updated_at;

        public RentalBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RentalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RentalBuilder surface(Float surface) {
            this.surface = surface;
            return this;
        }

        public RentalBuilder price(Float price) {
            this.price = price;
            return this;
        }

        public RentalBuilder picture(String picture) {
            this.picture = picture;
            return this;
        }

        public RentalBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RentalBuilder owner(Integer owner_id) {
            this.owner_id = owner_id;
            return this;
        }

        public RentalBuilder createdAt(String created_at) {
            this.created_at = created_at;
            return this;
        }

        public RentalBuilder updatedAt(String updated_at) {
            this.updated_at = updated_at;
            return this;
        }
        public RentalDto build() {
           return new RentalDto(this);
        }
    }
}
