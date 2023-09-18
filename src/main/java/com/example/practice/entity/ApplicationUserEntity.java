package com.example.practice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "application_user")
public class ApplicationUserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "e_mail")
    private String email;

    @ManyToMany(mappedBy = "applicationUserEntities")
    private List<LetterEntity> userLetters;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m2m_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> userRoles;
    @OneToMany(mappedBy = "createdBy")
    private List<LetterEntity> sentLetters;
    @OneToMany(mappedBy = "applicationUserEntity")
    private List<IncomingLetterEntity> incomingLettersEntities;


    public ApplicationUserEntity() {
        this.userRoles = new ArrayList<>();
        this.userLetters = new ArrayList<>();
        this.sentLetters = new ArrayList<>();
        this.incomingLettersEntities = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ApplicationUserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ApplicationUserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getUserRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ApplicationUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<LetterEntity> getUserLetters() {
        return userLetters;
    }

    public ApplicationUserEntity setUserLetters(List<LetterEntity> userLetters) {
        this.userLetters = userLetters;
        return this;
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public ApplicationUserEntity setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public List<LetterEntity> getSentLetters() {
        return sentLetters;
    }

    public ApplicationUserEntity setSentLetters(List<LetterEntity> sentLetters) {
        this.sentLetters = sentLetters;
        return this;
    }

    public List<IncomingLetterEntity> getIncomingLettersEntities() {
        return incomingLettersEntities;
    }

    public ApplicationUserEntity setIncomingLettersEntities(List<IncomingLetterEntity> incomingLettersEntities) {
        this.incomingLettersEntities = incomingLettersEntities;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserEntity that = (ApplicationUserEntity) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userRoles, that.userRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, userRoles);
    }
}
