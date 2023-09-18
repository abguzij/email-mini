package com.example.practice.dto;

public class UserCredentialsDto {
    private String username;
    private String password;

    public UserCredentialsDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserCredentialsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCredentialsDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
