package com.example.practice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "public", name = "letter")
public class LetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "letter_text")
    private String text;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "m2m_user_letter",
            joinColumns = @JoinColumn(name = "letter_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<ApplicationUserEntity> applicationUserEntities;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "letterEntity")
    private List<IncomingLetterEntity> incomingLettersEntities;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_by")
    private ApplicationUserEntity createdBy;

    public LetterEntity() {
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LetterEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LetterEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getText() {
        return text;
    }

    public LetterEntity setText(String text) {
        this.text = text;
        return this;
    }

    public List<ApplicationUserEntity> getApplicationUserEntities() {
        return applicationUserEntities;
    }

    public LetterEntity setApplicationUserEntities(List<ApplicationUserEntity> applicationUserEntities) {
        this.applicationUserEntities = applicationUserEntities;
        return this;
    }

    public List<IncomingLetterEntity> getIncomingLettersEntities() {
        return incomingLettersEntities;
    }

    public LetterEntity setIncomingLettersEntities(List<IncomingLetterEntity> incomingLettersEntities) {
        this.incomingLettersEntities = incomingLettersEntities;
        return this;
    }

    public ApplicationUserEntity getCreatedBy() {
        return createdBy;
    }

    public LetterEntity setCreatedBy(ApplicationUserEntity createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
