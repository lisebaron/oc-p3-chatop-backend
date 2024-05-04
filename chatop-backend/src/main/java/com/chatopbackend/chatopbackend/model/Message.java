package com.chatopbackend.chatopbackend.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String message;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Message() {
    }

    public Message(Rental rental, User user, String message) {
        this.rental = rental;
        this.user = user;
        this.message = message;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }
    public Rental getRental_id() {
        return rental;
    }
    public User getUser_id() {
        return user;
    }
    public String getMessage() {
        return message;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}