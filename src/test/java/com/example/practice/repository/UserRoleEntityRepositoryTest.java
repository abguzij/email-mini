package com.example.practice.repository;

import com.example.practice.entity.UserRoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
class UserRoleEntityRepositoryTest {
    @Autowired
    private UserRoleEntityRepository userRoleEntityRepository;

    @Test
    public void testSave_OK() {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRoleTitle("TEST");
        UserRoleEntity userRoleEntity = this.userRoleEntityRepository.save(userRole);
        Assertions.assertEquals(userRole.getRoleTitle(), userRoleEntity.getRoleTitle());
    }
}