package com.example.practice.response;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

public class SuccessResponse {
    private HttpStatus httpStatus;
    private String message;

    public SuccessResponse() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public SuccessResponse setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SuccessResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
