package com.example.practice.controller;

import com.example.practice.configuration.SecurityConfigurationTest;
import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.dto.JwtTokenResponseDto;
import com.example.practice.dto.UserCredentialsDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import com.example.practice.security.JwtAuthenticationFilter;
import com.example.practice.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true"
)
@ContextConfiguration(classes = SecurityConfigurationTest.class)
@TestPropertySource(value = "classpath:test.properties")
class AuthenticationControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @LocalServerPort
    private int port;
    private String jwtToken;

    @Test
    public void testLogin_OK() {
        try {
            URI uri = new URI( "http://localhost:" + port + "/auth/signin");
            UserCredentialsDto userCredentialsDto = new UserCredentialsDto()
                    .setUsername(SecurityConfigurationTest.TEST_CUSTOMER_USERNAME)
                    .setPassword(SecurityConfigurationTest.TEST_CUSTOMER_PASSWORD);

            JwtTokenResponseDto jwtTokenResponseDto =
                    this.testRestTemplate.postForObject(uri, userCredentialsDto, JwtTokenResponseDto.class);
            JwtTokenResponseDto jwtTokenResponseDtoTest =
                    this.authenticationService.login(
                            SecurityConfigurationTest.TEST_CUSTOMER_USERNAME,
                            SecurityConfigurationTest.TEST_CUSTOMER_PASSWORD
                    );

            Assertions.assertEquals(
                    jwtTokenResponseDtoTest.getJwtTokenValue(), jwtTokenResponseDto.getJwtTokenValue()
            );
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testRegisterUser_OK() {
        try {
            URI uri = new URI( "http://localhost:" + port + "/auth/register");
            ApplicationUserRequestDto applicationUserRequestDto = new ApplicationUserRequestDto()
                    .setUsername("username")
                    .setPassword("password")
                    .setEmail("user@gmail.com");

            ApplicationUserResponseDto responseDto = this.testRestTemplate.postForObject(
                    uri,
                    applicationUserRequestDto,
                    ApplicationUserResponseDto.class
            );

            Assertions.assertEquals("username", responseDto.getUsername());
            Assertions.assertEquals("user@gmail.com", responseDto.getEmail());
            Assertions.assertEquals(1L, responseDto.getId());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}