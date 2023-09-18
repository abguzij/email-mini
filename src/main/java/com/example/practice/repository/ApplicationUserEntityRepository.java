package com.example.practice.repository;

import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationUserEntityRepository
        extends JpaRepository<ApplicationUserEntity, Long>
{
    Optional<ApplicationUserEntity> getApplicationUserEntityByUsername(String username);

    Optional<ApplicationUserEntity> getApplicationUserEntityByEmail(String email);

    @Query(value = "select * from application_user join user_rolegroup by ;", nativeQuery = true)
    Integer getNumberOfUserWithConcreteRole(String roleTitle);
}
