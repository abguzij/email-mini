package com.example.practice.controller.v1;

import com.example.practice.dto.IncomingLetterResponseDto;
import com.example.practice.dto.LetterRequestDto;
import com.example.practice.dto.LetterResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.IncomingLetterEntity;
import com.example.practice.exception.IncomingLettersSearchException;
import com.example.practice.mapper.LetterInfoMapper;
import com.example.practice.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/letters")
@PreAuthorize(value = "hasRole('CUSTOMER')")
public class LetterController {
    private final LetterService letterService;

    @Autowired
    public LetterController(
            LetterService letterService
    ) {
        this.letterService = letterService;
    }

    @PostMapping(value = "/send")
    public LetterResponseDto sendLetters(
            @RequestBody LetterRequestDto letterRequestDto
    ) {
        return this.letterService.sendNewLetter(letterRequestDto);
    }

    @GetMapping(value = "/unread")
    public List<IncomingLetterResponseDto> getAllUnreadLetters() throws IncomingLettersSearchException {
        return this.letterService.readUnreadEmails();
    }

    @GetMapping(value = "/all")
    public List<IncomingLetterResponseDto> getAllLetters() throws IncomingLettersSearchException {
        ApplicationUserEntity currentUser =
                (ApplicationUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<IncomingLetterEntity> incomingLetterEntities =
                this.letterService.getAllEmailsByUserId(currentUser.getId());
        List<IncomingLetterResponseDto> letterRequestDtoList =
                LetterInfoMapper.mapIncomingLettersEntityListToDtoList(incomingLetterEntities);

        this.letterService.readUnreadEmails();

        return letterRequestDtoList;
    }
}
