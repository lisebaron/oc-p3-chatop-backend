package com.chatopbackend.chatopbackend.dto;

import com.chatopbackend.chatopbackend.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDto {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String createdAt;
    private String updatedAt;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.createdAt = convertDateToString(user.getCreatedAt());
        this.updatedAt = convertDateToString(user.getUpdatedAt());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
