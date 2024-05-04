package com.chatopbackend.chatopbackend.dto;

public class MessageDto {
    private Integer id;
    private Integer rental_id;
    private Integer user_id;
    private String message;
    private String createdAt;
    private String updatedAt;

    public MessageDto(String message, Integer user_id, Integer rental_id ) {
        this.rental_id = rental_id;
        this.user_id = user_id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRentalId() {
        return rental_id;
    }

    public void setRentalId(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}