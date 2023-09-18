package com.example.practice.service;

import com.example.practice.configuration.SecurityConfigurationTest;
import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.dto.JwtTokenResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import com.example.practice.matchers.ApplicationUserEntityArgumentMatcher;
import com.example.practice.repository.ApplicationUserEntityRepository;
import com.example.practice.repository.UserRoleEntityRepository;
import com.example.practice.security.JwtTokenHandler;
import com.example.practice.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.*;

@ContextConfiguration(classes = SecurityConfigurationTest.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@ExtendWith(value = MockitoExtension.class)
@TestPropertySource(value = "classpath:test.properties")
public class AuthenticationServiceTest {
    public static final String DEFAULT_PASSWORD = "test";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenHandler jwtTokenHandler;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private ApplicationUserEntityRepository applicationUserEntityRepository;
    @MockBean
    private UserRoleEntityRepository userRoleEntityRepository;

    @Test
    public void testRegisterNewApplicationUser_OK() {
        ApplicationUserRequestDto dto = new ApplicationUserRequestDto();
        dto
                .setUsername("test")
                .setPassword("test")
                .setEmail("test@gmail.com");

        Mockito
                .when(this.userRoleEntityRepository.getUserRoleEntityByRoleTitle("CUSTOMER"))
                .thenReturn(
                        new UserRoleEntity().setId(1L).setRoleTitle("CUSTOMER")
                );


        ApplicationUserEntity applicationUser = new ApplicationUserEntity()
                .setUsername("test")
                .setPassword(DEFAULT_PASSWORD)
                .setEmail("test@gmail.com");
        applicationUser.getUserRoles().add(new UserRoleEntity().setId(1L).setRoleTitle("CUSTOMER"));

        applicationUser.setId(1L);
        Mockito
                .when(
                        this.applicationUserEntityRepository.save(
                                eq(applicationUser)
                                //argThat(new ApplicationUserEntityArgumentMatcher(applicationUser, this.passwordEncoder))
                        )
                )
                .thenReturn(applicationUser);


        AuthenticationService authenticationService = new AuthenticationServiceImpl(
                this.passwordEncoder,
                this.userDetailsService,
                this.jwtTokenHandler,
                this.applicationUserEntityRepository,
                this.userRoleEntityRepository
        );

        ApplicationUserResponseDto responseDto = authenticationService.registerNewApplicationUser(dto);
        Assertions.assertTrue(Objects.nonNull(responseDto));
        Assertions.assertEquals(1L, responseDto.getId());
        Assertions.assertEquals("test", responseDto.getUsername());
        Assertions.assertEquals("test@gmail.com", responseDto.getEmail());
    }

    @Test
    public void testLogin_OK() {
        String testUsername = "test";
        String testPassword = "test";

        AuthenticationService authenticationService = new AuthenticationServiceImpl(
                this.passwordEncoder,
                this.userDetailsService,
                this.jwtTokenHandler,
                this.applicationUserEntityRepository,
                this.userRoleEntityRepository
        );

        ApplicationUserEntity applicationUser = new ApplicationUserEntity()
                .setUsername("test")
                .setPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .setEmail("test@gmail.com");
        applicationUser.getUserRoles().add(new UserRoleEntity().setId(1L).setRoleTitle("CUSTOMER"));

        Mockito
                .when(this.userDetailsService.loadUserByUsername(eq(applicationUser.getUsername())))
                .thenReturn(applicationUser);

        String jwtToken = this.jwtTokenHandler.generateToken(
                UsernamePasswordAuthenticationToken.authenticated(
                        applicationUser,
                        null,
                        applicationUser.getAuthorities()
                )
        );

        JwtTokenResponseDto jwtTokenResponseDto = authenticationService.login(testUsername, testPassword);

        Assertions.assertEquals(jwtToken, jwtTokenResponseDto.getJwtTokenValue());
    }
}