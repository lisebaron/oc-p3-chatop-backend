package com.chatopbackend.chatopbackend.dto;

public class MessageDto {
    private Integer id;
    private Integer user_id;
    private Integer rental_id;
    private String message;
    private String createdAt;
    private String updatedAt;

    public MessageDto() {
    }
    private MessageDto(MessageBuilder builder) {
        this.id = builder.id;
        this.user_id = builder.user_id;
        this.rental_id = builder.rental_id;
        this.message = builder.message;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
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

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {
        private Integer id;
        private Integer user_id;
        private Integer rental_id;
        private String message;
        private String createdAt;
        private String updatedAt;

        public MessageBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public MessageBuilder user_id(Integer user_id) {
            this.user_id = user_id;
            return this;
        }

        public MessageBuilder rental_id(Integer rental_id) {
            this.rental_id = rental_id;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MessageBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MessageBuilder updatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }
}