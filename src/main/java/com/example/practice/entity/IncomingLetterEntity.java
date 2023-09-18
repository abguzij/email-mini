package com.example.practice.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "incoming_letters")
public class IncomingLetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_read")
    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ApplicationUserEntity applicationUserEntity;
    @ManyToOne
    @JoinColumn(name = "letter_id")
    private LetterEntity letterEntity;

    public IncomingLetterEntity() {
    }

    @PrePersist
    private void prePersist() {
        this.isRead = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public IncomingLetterEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getRead() {
        return isRead;
    }

    public IncomingLetterEntity setRead(Boolean read) {
        isRead = read;
        return this;
    }

    public ApplicationUserEntity getApplicationUserEntity() {
        return applicationUserEntity;
    }

    public IncomingLetterEntity setApplicationUserEntity(ApplicationUserEntity applicationUserEntity) {
        this.applicationUserEntity = applicationUserEntity;
        return this;
    }

    public LetterEntity getLetterEntity() {
        return letterEntity;
    }

    public IncomingLetterEntity setLetterEntity(LetterEntity letterEntities) {
        this.letterEntity = letterEntities;
        return this;
    }
}
