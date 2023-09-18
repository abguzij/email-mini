package com.example.practice.repository;

import com.example.practice.entity.IncomingLetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncomingLettersEntityRepository
        extends JpaRepository<IncomingLetterEntity, Long>
{
    @Query(
            value = "SELECT letter FROM IncomingLetterEntity letter" +
                    " WHERE letter.applicationUserEntity.id = :userId AND letter.isRead = false"
    )
    List<IncomingLetterEntity> getUnreadIncomingLettersEntitiesByUserId(@Param(value = "userId") Long userId);

    List<IncomingLetterEntity> getIncomingLettersEntitiesByApplicationUserEntity_Id(Long userId);
}
