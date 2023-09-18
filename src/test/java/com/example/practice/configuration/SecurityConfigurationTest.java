package com.example.practice.configuration;

import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import com.example.practice.repository.ApplicationUserEntityRepository;
import com.example.practice.repository.UserRoleEntityRepository;
import com.example.practice.security.JwtAuthEntryPoint;
import com.example.practice.security.JwtAuthenticationFilter;
import com.example.practice.security.JwtTokenHandler;
import com.example.practice.service.AuthenticationService;
import com.example.practice.service.impl.AuthenticationServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestConfiguration
@TestPropertySource(value = "classpath:test.properties")
public class SecurityConfigurationTest {

    public static final String TEST_CUSTOMER_USERNAME = "customer_username";
    public static final String TEST_CUSTOMER_PASSWORD = "customer_password";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        ApplicationUserEntity userDetails = new ApplicationUserEntity()
                .setId(1L)
                .setUsername(TEST_CUSTOMER_USERNAME)
                .setPassword(passwordEncoder.encode(TEST_CUSTOMER_PASSWORD));
        userDetails.getUserRoles().add(new UserRoleEntity().setId(1L).setRoleTitle("CUSTOMER"));

        return new InMemoryUserDetailsManager(userDetails);
    }
}