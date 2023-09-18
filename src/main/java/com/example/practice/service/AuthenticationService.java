package com.example.practice.service;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.dto.JwtTokenResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthenticationService {
    @Transactional
    ApplicationUserResponseDto registerNewApplicationUser (ApplicationUserRequestDto applicationUserRequestDto);

    JwtTokenResponseDto login(String username, String password);
}
