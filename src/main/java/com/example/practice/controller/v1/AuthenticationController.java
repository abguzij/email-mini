package com.example.practice.controller.v1;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.dto.JwtTokenResponseDto;
import com.example.practice.dto.UserCredentialsDto;
import com.example.practice.exception.ApplicationUserInfoValidationException;
import com.example.practice.service.AuthenticationService;
import com.example.practice.validator.ApplicationUserInfoValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ApplicationUserInfoValidator applicationUserInfoValidator;

    public AuthenticationController(
            AuthenticationService authenticationService,
            ApplicationUserInfoValidator applicationUserInfoValidator
    ) {
        this.authenticationService = authenticationService;
        this.applicationUserInfoValidator = applicationUserInfoValidator;
    }

    @PostMapping(value = "/signin")
    public JwtTokenResponseDto login(
            @RequestBody UserCredentialsDto userCredentialsDto
    ){
        return this.authenticationService.login(
                userCredentialsDto.getUsername(),
                userCredentialsDto.getPassword()
        );
    }

    @PostMapping(value = "/register")
    public ApplicationUserResponseDto registerUser(
            @RequestBody ApplicationUserRequestDto applicationUserRequestDto
    )
            throws ApplicationUserInfoValidationException
    {
        this.applicationUserInfoValidator.validateUserCredentials(applicationUserRequestDto);
        return this.authenticationService.registerNewApplicationUser(applicationUserRequestDto);
    }
}
