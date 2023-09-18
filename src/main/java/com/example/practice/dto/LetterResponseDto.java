package com.example.practice.dto;

import java.util.List;

public class LetterResponseDto {
    private Long id;
    private List<String> emails;
    private String text;
    private ApplicationUserResponseDto createdBy;

    public LetterResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public LetterResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public List<String> getEmails() {
        return emails;
    }

    public LetterResponseDto setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }

    public String getText() {
        return text;
    }

    public LetterResponseDto setText(String text) {
        this.text = text;
        return this;
    }

    public ApplicationUserResponseDto getCreatedBy() {
        return createdBy;
    }

    public LetterResponseDto setCreatedBy(ApplicationUserResponseDto createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
