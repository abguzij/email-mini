package com.example.practice.dto;

public class ApplicationUserRequestDto {
    private String username;
    private String password;
    private String email;

    public ApplicationUserRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public ApplicationUserRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUserRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUserRequestDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
