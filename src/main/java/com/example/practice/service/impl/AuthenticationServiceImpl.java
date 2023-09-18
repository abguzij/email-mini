package com.example.practice.service.impl;

import com.example.practice.dto.ApplicationUserRequestDto;
import com.example.practice.dto.ApplicationUserResponseDto;
import com.example.practice.dto.JwtTokenResponseDto;
import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.mapper.ApplicationUserInfoMapper;
import com.example.practice.repository.ApplicationUserEntityRepository;
import com.example.practice.repository.UserRoleEntityRepository;
import com.example.practice.security.JwtTokenHandler;
import com.example.practice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenHandler jwtTokenHandler;
    private final ApplicationUserEntityRepository applicationUserEntityRepository;
    private final UserRoleEntityRepository userRoleEntityRepository;
    @Autowired
    public AuthenticationServiceImpl(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            JwtTokenHandler jwtTokenHandler,
            ApplicationUserEntityRepository applicationUserEntityRepository,
            UserRoleEntityRepository userRoleEntityRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtTokenHandler = jwtTokenHandler;
        this.applicationUserEntityRepository = applicationUserEntityRepository;
        this.userRoleEntityRepository = userRoleEntityRepository;
    }

    @Override
    public ApplicationUserResponseDto registerNewApplicationUser(ApplicationUserRequestDto applicationUserRequestDto) {
        ApplicationUserEntity applicationUser =
                ApplicationUserInfoMapper.mapApplicationUserRequestDtoToEntity(applicationUserRequestDto);

        applicationUser.getUserRoles().add(this.userRoleEntityRepository.getUserRoleEntityByRoleTitle("CUSTOMER"));
        applicationUser.setPassword(this.passwordEncoder.encode(applicationUser.getPassword()));

        return ApplicationUserInfoMapper.mapApplicationUserEntityToDto(
                this.applicationUserEntityRepository.save(applicationUser)
        );
    }

    @Override
    public JwtTokenResponseDto login(String username, String password) {
        JwtTokenResponseDto responseDto = new JwtTokenResponseDto();
        UserDetails authenticatedUser = this.userDetailsService.loadUserByUsername(username);
        if(this.passwordEncoder.matches(password, authenticatedUser.getPassword())){
            Authentication authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            authenticatedUser,
                            null,
                            authenticatedUser.getAuthorities()
                    );
            return responseDto.setJwtTokenValue(this.jwtTokenHandler.generateToken(authentication));
        }
        return responseDto.setJwtTokenValue("Incorrect Password or Username");
    }
}
