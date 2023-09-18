package com.example.practice.dto;

public class IncomingLetterResponseDto {
    private Long id;
    private String text;
    private String authorsUsername;
    private String authorsEmail;
    private Boolean isRead;

    public IncomingLetterResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public IncomingLetterResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public IncomingLetterResponseDto setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthorsUsername() {
        return authorsUsername;
    }

    public IncomingLetterResponseDto setAuthorsUsername(String authorsUsername) {
        this.authorsUsername = authorsUsername;
        return this;
    }

    public String getAuthorsEmail() {
        return authorsEmail;
    }

    public IncomingLetterResponseDto setAuthorsEmail(String authorsEmail) {
        this.authorsEmail = authorsEmail;
        return this;
    }

    public Boolean getRead() {
        return isRead;
    }

    public IncomingLetterResponseDto setRead(Boolean read) {
        isRead = read;
        return this;
    }
}
