package com.example.practice.dto;

import java.util.List;

public class LetterRequestDto {
    private String text;
    private List<String> emails;

    public LetterRequestDto() {
    }

    public String getText() {
        return text;
    }

    public LetterRequestDto setText(String text) {
        this.text = text;
        return this;
    }

    public List<String> getEmails() {
        return emails;
    }

    public LetterRequestDto setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }
}
