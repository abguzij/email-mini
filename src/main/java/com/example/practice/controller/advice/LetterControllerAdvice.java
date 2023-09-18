package com.example.practice.controller.advice;

import com.example.practice.controller.v1.LetterController;
import com.example.practice.exception.IncomingLettersSearchException;
import com.example.practice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = LetterController.class)
public class LetterControllerAdvice {
    @ExceptionHandler(value = IncomingLettersSearchException.class)
    public ErrorResponse handleIncomingLettersSearchException(IncomingLettersSearchException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getMessage());
    }
}
