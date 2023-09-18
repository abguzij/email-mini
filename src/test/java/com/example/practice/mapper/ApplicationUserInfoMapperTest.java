package com.example.practice.mapper;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApplicationUserInfoMapperTest {

    @Test
    public void testMapApplicationUserRequestDtoToEntity_OK() {
        ApplicationUserRequestDto dto = new ApplicationUserRequestDto();
        dto
                .setUsername("test")
                .setPassword("test")
                .setEmail("test@gmail.com");

        ApplicationUserEntity applicationUser = ApplicationUserInfoMapper.mapApplicationUserRequestDtoToEntity(dto);

        assert applicationUser.getUsername().equals("test");
        assert applicationUser.getPassword().equals("test");
        assert applicationUser.getEmail().equals("test@gmail.com");
    }

    @Test
    public void testMapApplicationUserEntityToDto() {
        ApplicationUserEntity userEntity = new ApplicationUserEntity();
        userEntity
                .setId(1L)
                .setUsername("test")
                .setEmail("test@gmail.com");

        ApplicationUserResponseDto dto = ApplicationUserInfoMapper.mapApplicationUserEntityToDto(userEntity);

        assert dto.getId().equals(1L);
        assert dto.getUsername().equals("test");
        assert dto.getEmail().equals("test@gmail.com");
    }
}
