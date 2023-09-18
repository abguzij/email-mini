package com.example.practice.service;

import com.example.practice.dto.IncomingLetterResponseDto;
import com.example.practice.dto.LetterRequestDto;
import com.example.practice.dto.LetterResponseDto;
import com.example.practice.entity.IncomingLetterEntity;
import com.example.practice.exception.IncomingLettersSearchException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LetterService {
    @Transactional
    LetterResponseDto sendNewLetter(LetterRequestDto letterRequestDto);

    @Transactional
    List<IncomingLetterResponseDto> readUnreadEmails() throws IncomingLettersSearchException;

    List<IncomingLetterEntity> getAllUnreadEmailsByUserId(Long userId) throws IncomingLettersSearchException;

    List<IncomingLetterEntity> getAllEmailsByUserId(Long userId) throws IncomingLettersSearchException;
}
