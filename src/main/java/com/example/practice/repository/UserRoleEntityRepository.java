package com.example.practice.repository;

import com.example.practice.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleEntityRepository
        extends JpaRepository<UserRoleEntity, Long>
{
    UserRoleEntity getUserRoleEntityByRoleTitle(String roleTitle);
}
