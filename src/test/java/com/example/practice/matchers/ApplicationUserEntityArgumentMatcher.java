package com.example.practice.matchers;

import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import com.example.practice.service.AuthenticationService;
import com.example.practice.service.AuthenticationServiceTest;
import org.mockito.ArgumentMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class ApplicationUserEntityArgumentMatcher implements ArgumentMatcher<ApplicationUserEntity> {
    private ApplicationUserEntity left;
    private PasswordEncoder passwordEncoder;

    public ApplicationUserEntityArgumentMatcher(ApplicationUserEntity left, PasswordEncoder passwordEncoder) {
        this.left = left;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean matches(ApplicationUserEntity right) {
        return left.getUsername().equals(right.getUsername()) &&
                passwordEncoder.matches(AuthenticationServiceTest.DEFAULT_PASSWORD, right.getPassword()) &&
                left.getEmail().equals(right.getEmail()) &&
                left.getUserRoles().equals(right.getUserRoles());
    }
}
