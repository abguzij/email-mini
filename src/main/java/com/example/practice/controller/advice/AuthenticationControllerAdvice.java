package com.example.practice.controller.advice;

import com.example.practice.controller.v1.AuthenticationController;
import com.example.practice.exception.ApplicationUserInfoValidationException;
import com.example.practice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(value = ApplicationUserInfoValidationException.class)
    public ErrorResponse handleApplicationUserInfoValidationException(ApplicationUserInfoValidationException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ErrorResponse handleApplicationUserInfoValidationException(UsernameNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getMessage());
    }
}
