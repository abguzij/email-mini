package com.example.practice.repository;

import com.example.practice.entity.ApplicationUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class ApplicationUserEntityRepositoryTest {
    @Autowired
    private ApplicationUserEntityRepository applicationUserEntityRepository;
    @Test
    public void testGetApplicationUserEntityByUsername_OK() {
    }

    @Test
    public void testSave_OK() {
        ApplicationUserEntity applicationUser = new ApplicationUserEntity()
                .setUsername("test");
        this.applicationUserEntityRepository.save(applicationUser);
    }
}