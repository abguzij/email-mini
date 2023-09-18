package com.example.practice.validator;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.exception.ApplicationUserInfoValidationException;

public interface ApplicationUserInfoValidator {
    void validateUserCredentials(ApplicationUserRequestDto dto) throws ApplicationUserInfoValidationException;
}
