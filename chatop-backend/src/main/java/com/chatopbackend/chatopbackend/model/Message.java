package com.chatopbackend.chatopbackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Message() {
    }

    public Message(Rental rental, User user, String message) {
        this.rental = rental;
        this.user = user;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }
    public Rental getRental() {
        return rental;
    }
    public User getUser() {
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