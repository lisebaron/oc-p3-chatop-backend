package com.chatopbackend.chatopbackend.dto;

import com.chatopbackend.chatopbackend.model.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDto {
    private Integer id;
    private Integer user_id;
    private Integer rental_id;
    private String message;
    private String createdAt;
    private String updatedAt;

    public MessageDto(String message, Integer user_id, Integer rental_id ) {
        this.rental_id = rental_id;
        this.user_id = user_id;
        this.message = message;
    }

    public MessageDto(Message message) {
        this.id = message.getId();
        this.rental_id = message.getRental().getId();
        this.user_id = message.getUser().getId();
        this.message = message.getMessage();
        this.createdAt = convertDateToString(message.getCreatedAt());
        this.updatedAt = convertDateToString(message.getUpdatedAt());
    }

    public MessageDto() {
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

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
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