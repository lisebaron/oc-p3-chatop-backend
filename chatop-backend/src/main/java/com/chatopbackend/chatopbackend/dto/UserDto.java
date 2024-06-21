package com.chatopbackend.chatopbackend.dto;

public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String created_at;
    private String updated_at;

    public UserDto() {
    }

    public UserDto(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.created_at = builder.created_at;
        this.updated_at = builder.updated_at;
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

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private String name;
        private String email;
        private String password;
        private String created_at;
        private String updated_at;

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder created_at(String created_at) {
            this.created_at = created_at;
            return this;
        }

        public UserBuilder updated_at(String updated_at) {
            this.updated_at = updated_at;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
