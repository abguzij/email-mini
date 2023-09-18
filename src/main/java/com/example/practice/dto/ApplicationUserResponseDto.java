package com.example.practice.dto;

public class ApplicationUserResponseDto {
    private Long id;
    private String username;
    private String email;

    public ApplicationUserResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public ApplicationUserResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ApplicationUserResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUserResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
