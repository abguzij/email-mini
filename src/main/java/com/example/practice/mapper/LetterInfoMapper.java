package com.example.practice.mapper;

import com.example.practice.dto.IncomingLetterResponseDto;
import com.example.practice.dto.LetterRequestDto;
import com.example.practice.dto.LetterResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.IncomingLetterEntity;
import com.example.practice.entity.LetterEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LetterInfoMapper {

    public static LetterEntity mapLetterRequestDtoToEntity(LetterRequestDto dto) {
        return new LetterEntity()
                .setText(dto.getText());
    }

    public static LetterResponseDto mapLetterEntityToDto(LetterEntity source) {
        return new LetterResponseDto()
                .setId(source.getId())
                .setText(source.getText())
                .setCreatedBy(ApplicationUserInfoMapper.mapApplicationUserEntityToDto(source.getCreatedBy()))
                .setEmails(
                        LetterInfoMapper.getEmailsOfApplicationUsers(source.getApplicationUserEntities())
                );
    }

    public static IncomingLetterResponseDto mapIncomingLetterEntityToDto(IncomingLetterEntity source) {
        return new IncomingLetterResponseDto()
                .setId(source.getId())
                .setText(source.getLetterEntity().getText())
                .setRead(source.getRead())
                .setAuthorsEmail(source.getLetterEntity().getCreatedBy().getEmail())
                .setAuthorsUsername(source.getLetterEntity().getCreatedBy().getUsername());
    }

    public static List<IncomingLetterResponseDto> mapIncomingLettersEntityListToDtoList(
            List<IncomingLetterEntity> sourceList
    ) {
        return sourceList
                .stream()
                .map(LetterInfoMapper::mapIncomingLetterEntityToDto)
                .collect(Collectors.toList());
    }

    private static List<String> getEmailsOfApplicationUsers(List<ApplicationUserEntity> applicationUserEntities) {
        return applicationUserEntities
                .stream()
                .map(ApplicationUserEntity::getEmail)
                .collect(Collectors.toList());
    }
}
