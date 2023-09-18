package com.example.practice.validator.impl;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.exception.ApplicationUserInfoValidationException;
import com.example.practice.repository.ApplicationUserEntityRepository;
import com.example.practice.validator.ApplicationUserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationUserInfoValidatorImpl implements ApplicationUserInfoValidator {
    private final ApplicationUserEntityRepository applicationUserEntityRepository;

    @Autowired
    public ApplicationUserInfoValidatorImpl(
            ApplicationUserEntityRepository applicationUserEntityRepository
    ) {
        this.applicationUserEntityRepository = applicationUserEntityRepository;
    }

    @Override
    public void validateUserCredentials(ApplicationUserRequestDto dto)
            throws ApplicationUserInfoValidationException
    {
        this.validateUsername(dto.getUsername());

        String email = dto.getEmail();
        this.validateEmailFormat(email);
        this.validateUserEmail(email);
    }

    private void validateEmailFormat(String email) throws ApplicationUserInfoValidationException {
        if(!email.endsWith("@gmail.com")) {
            throw new ApplicationUserInfoValidationException(
                    "Почтовый адрес пользователя должен оканчиваться на '@gmail.com'"
            );
        }
    }

    private void validateUsername(String username) throws ApplicationUserInfoValidationException {
        Optional<ApplicationUserEntity> userEntityOptional =
                this.applicationUserEntityRepository.getApplicationUserEntityByUsername(username);
        if(userEntityOptional.isPresent()) {
            throw new ApplicationUserInfoValidationException("Пользователь с таким именем уже существует!");
        }
     }

    private void validateUserEmail(String email) throws ApplicationUserInfoValidationException {
        Optional<ApplicationUserEntity> userEntityOptional =
                this.applicationUserEntityRepository.getApplicationUserEntityByEmail(email);

        if(userEntityOptional.isPresent()) {
            throw new ApplicationUserInfoValidationException("Пользователь с таким почтовым адресом уже существует!");
        }
    }
}
