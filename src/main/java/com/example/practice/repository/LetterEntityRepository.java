package com.example.practice.repository;

import com.example.practice.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterEntityRepository
        extends JpaRepository<LetterEntity, Long>
{
}
