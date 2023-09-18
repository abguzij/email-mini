package com.example.practice.service.impl;

import com.example.practice.dto.IncomingLetterResponseDto;
import com.example.practice.dto.LetterRequestDto;
import com.example.practice.dto.LetterResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.IncomingLetterEntity;
import com.example.practice.entity.LetterEntity;
import com.example.practice.exception.IncomingLettersSearchException;
import com.example.practice.mapper.LetterInfoMapper;
import com.example.practice.repository.IncomingLettersEntityRepository;
import com.example.practice.repository.LetterEntityRepository;
import com.example.practice.service.ApplicationUserService;
import com.example.practice.service.AuthenticationService;
import com.example.practice.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LetterServiceImpl implements LetterService {
    private final ApplicationUserService applicationUserService;
    private final LetterEntityRepository letterEntityRepository;
    private final IncomingLettersEntityRepository incomingLettersEntityRepository;

    @Autowired
    public LetterServiceImpl(
            ApplicationUserService applicationUserService,
            LetterEntityRepository letterEntityRepository,
            IncomingLettersEntityRepository incomingLettersEntityRepository
    ) {
        this.applicationUserService = applicationUserService;
        this.letterEntityRepository = letterEntityRepository;
        this.incomingLettersEntityRepository = incomingLettersEntityRepository;
    }

    @Override
    public LetterResponseDto sendNewLetter(LetterRequestDto requestDto) {
        LetterEntity letter = LetterInfoMapper.mapLetterRequestDtoToEntity(requestDto);
        ApplicationUserEntity author =
                (ApplicationUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        letter.setCreatedBy(author);
        letter.setApplicationUserEntities(
                this.applicationUserService.getExistingApplicationUsersByEmails(requestDto.getEmails())
        );

        List<IncomingLetterEntity> incomingLettersEntities = new ArrayList<>();
        for (ApplicationUserEntity destination : letter.getApplicationUserEntities()) {
            IncomingLetterEntity incomingLetterEntity = new IncomingLetterEntity();
            incomingLetterEntity.setLetterEntity(letter);
            incomingLetterEntity.setApplicationUserEntity(destination);
            incomingLettersEntities.add(incomingLetterEntity);
        }

        letter = this.letterEntityRepository.save(letter);
        this.incomingLettersEntityRepository.saveAll(incomingLettersEntities);

        return LetterInfoMapper.mapLetterEntityToDto(letter);
    }

    @Override
    public List<IncomingLetterResponseDto> readUnreadEmails() throws IncomingLettersSearchException {
        ApplicationUserEntity currentUser =
                (ApplicationUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<IncomingLetterEntity> incomingLetterEntities =
                this.getAllUnreadEmailsByUserId(currentUser.getId());
        List<IncomingLetterResponseDto> letterResponseDtoList =
                LetterInfoMapper.mapIncomingLettersEntityListToDtoList(incomingLetterEntities);

        incomingLetterEntities = incomingLetterEntities
                .stream()
                .peek(letterEntity -> {
                    if (!letterEntity.getRead()) {
                        letterEntity.setRead(Boolean.TRUE);
                    }
                })
                .collect(Collectors.toList());
        this.incomingLettersEntityRepository.saveAll(incomingLetterEntities);
        return letterResponseDtoList;
    }

    @Override
    public List<IncomingLetterEntity> getAllUnreadEmailsByUserId(Long userId) throws IncomingLettersSearchException {
        List<IncomingLetterEntity> incomingLettersEntities =
                this.incomingLettersEntityRepository.getUnreadIncomingLettersEntitiesByUserId(userId);
        if (Objects.isNull(incomingLettersEntities) || incomingLettersEntities.isEmpty()){
            throw new IncomingLettersSearchException(
                    "You don't have any unread emails!"
            );
        }
        return incomingLettersEntities;
    }

    @Override
    public List<IncomingLetterEntity> getAllEmailsByUserId(Long userId) throws IncomingLettersSearchException {
        List<IncomingLetterEntity> incomingLettersEntities =
                this.incomingLettersEntityRepository.getIncomingLettersEntitiesByApplicationUserEntity_Id(userId);
        if (Objects.isNull(incomingLettersEntities) || incomingLettersEntities.isEmpty()){
            throw new IncomingLettersSearchException(
                    "You haven't received any emails yet!"
            );
        }
        return incomingLettersEntities;
    }
}
