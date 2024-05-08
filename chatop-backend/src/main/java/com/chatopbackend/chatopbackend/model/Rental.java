package com.chatopbackend.chatopbackend.model;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float surface;
    private Float price;
    private String picture;
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Rental() {
    }

    public Rental(String name, Float surface, Float price, String picture, String description, User owner) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
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
    public void setSurface(float surface) {
        this.surface = surface;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(float price) {
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

    public User getOwner() {
        return owner;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
