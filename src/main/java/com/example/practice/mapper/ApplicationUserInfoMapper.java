package com.example.practice.mapper;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import com.example.practice.repository.UserRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

public class ApplicationUserInfoMapper {

    public static ApplicationUserEntity mapApplicationUserRequestDtoToEntity(ApplicationUserRequestDto dto) {
        if(Objects.isNull(dto)) {
            throw new IllegalArgumentException("DTO must not be null!");
        }
        if(Objects.isNull(dto.getUsername()) || dto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty!");
        }
        if(Objects.isNull(dto.getPassword()) || dto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty!");
        }
        if(Objects.isNull(dto.getEmail()) || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty!");
        }
        if(!dto.getEmail().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email must ends with @gmail.com");
        }

        return new ApplicationUserEntity()
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setEmail(dto.getEmail());
    }

    public static ApplicationUserResponseDto mapApplicationUserEntityToDto(ApplicationUserEntity source) {
        if(Objects.isNull(source)) {
            throw new IllegalArgumentException("Source must not be null");
        }
        return new ApplicationUserResponseDto()
                .setId(source.getId())
                .setUsername(source.getUsername())
                .setEmail(source.getEmail());
    }
}
