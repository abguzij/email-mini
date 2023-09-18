package com.example.practice.dto;

public class JwtTokenResponseDto {
    private String jwtTokenValue;

    public JwtTokenResponseDto() {
    }

    public String getJwtTokenValue() {
        return jwtTokenValue;
    }

    public JwtTokenResponseDto setJwtTokenValue(String jwtTokenValue) {
        this.jwtTokenValue = jwtTokenValue;
        return this;
    }
}
