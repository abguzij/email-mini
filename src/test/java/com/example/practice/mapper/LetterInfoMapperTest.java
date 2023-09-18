package com.example.practice.mapper;

import com.example.practice.dto.IncomingLetterResponseDto;
import com.example.practice.dto.LetterRequestDto;
import com.example.practice.dto.LetterResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.IncomingLetterEntity;
import com.example.practice.entity.LetterEntity;
import com.example.practice.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

public class LetterInfoMapperTest {

    private static List<ApplicationUserEntity> letterRecipients;
    private static ApplicationUserEntity letterAuthor;
    private static ApplicationUserEntity recipient;
    private static LetterEntity testLetter;
    private static IncomingLetterEntity incomingLetterEntity;
    private static List<IncomingLetterEntity> incomingLetterEntities;

    @BeforeAll
    public static void beforeAll() {
        recipient = new ApplicationUserEntity()
                .setId(1L)
                .setEmail("recipient_1@gmail.com")
                .setUsername("recipient_1");

        letterRecipients = new ArrayList<>();
        letterRecipients.add(recipient);
        letterRecipients.add(
                new ApplicationUserEntity()
                        .setId(2L)
                        .setEmail("recipient_2@gmail.com")
                        .setUsername("recipient_2")
        );

        letterAuthor = new ApplicationUserEntity()
                .setId(3L)
                .setEmail("author@gmail.com")
                .setUsername("author");

        testLetter = new LetterEntity()
                .setId(1L)
                .setText("test_text")
                .setCreatedBy(letterAuthor)
                .setApplicationUserEntities(letterRecipients);

        incomingLetterEntity = new IncomingLetterEntity()
                .setId(1L)
                .setApplicationUserEntity(recipient)
                .setRead(Boolean.FALSE)
                .setLetterEntity(testLetter);

        incomingLetterEntities = new ArrayList<>();
        incomingLetterEntities.add(incomingLetterEntity);
        incomingLetterEntities.add(
                new IncomingLetterEntity()
                        .setId(2L)
                        .setApplicationUserEntity(letterRecipients.get(1))
                        .setRead(Boolean.FALSE)
                        .setLetterEntity(testLetter)
        );
    }

    @Test
    public void testMapLetterRequestDtoToEntity_OK() {
        LetterRequestDto requestDto = new LetterRequestDto();
        requestDto.setText("test_text");

        LetterEntity letter = LetterInfoMapper.mapLetterRequestDtoToEntity(requestDto);
        assert letter.getText().equals("test_text");
    }

    @Test
    public void testMapLetterEntityToDto_OK() {
        LetterResponseDto dto = LetterInfoMapper.mapLetterEntityToDto(testLetter);

        assert dto.getId().equals(1L);
        assert dto.getEmails().get(0).equals("recipient_1@gmail.com");
        assert dto.getEmails().get(1).equals("recipient_2@gmail.com");
        assert dto.getText().equals("test_text");

        assert dto.getCreatedBy().getId().equals(3L);
        assert dto.getCreatedBy().getUsername().equals("author");
        assert dto.getCreatedBy().getEmail().equals("author@gmail.com");
    }

    @Test
    public void testMapIncomingLetterEntityToDto_OK() {
        IncomingLetterResponseDto dto = LetterInfoMapper.mapIncomingLetterEntityToDto(incomingLetterEntity);

        assert dto.getId().equals(1L);
        assert dto.getRead().equals(Boolean.FALSE);
        assert dto.getText().equals("test_text");
        assert dto.getAuthorsEmail().equals("author@gmail.com");
        assert dto.getAuthorsUsername().equals("author");
    }

    @Test
    public void testMapIncomingLettersEntityListToDtoList_OK() {
        List<IncomingLetterResponseDto> incomingLetterResponseDtoList
                = LetterInfoMapper.mapIncomingLettersEntityListToDtoList(incomingLetterEntities);

        assert incomingLetterResponseDtoList.get(0).getId().equals(1L);
        assert incomingLetterResponseDtoList.get(0).getRead().equals(Boolean.FALSE);
        assert incomingLetterResponseDtoList.get(0).getText().equals("test_text");
        assert incomingLetterResponseDtoList.get(0).getAuthorsEmail().equals("author@gmail.com");
        assert incomingLetterResponseDtoList.get(0).getAuthorsUsername().equals("author");

        assert incomingLetterResponseDtoList.get(1).getId().equals(2L);
        assert incomingLetterResponseDtoList.get(1).getRead().equals(Boolean.FALSE);
        assert incomingLetterResponseDtoList.get(1).getText().equals("test_text");
        assert incomingLetterResponseDtoList.get(1).getAuthorsEmail().equals("author@gmail.com");
        assert incomingLetterResponseDtoList.get(1).getAuthorsUsername().equals("author");
    }
}