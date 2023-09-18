package com.example.practice.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "user_role")
public class UserRoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role_title")
    private String roleTitle;

    @ManyToMany(mappedBy = "userRoles")
    private List<ApplicationUserEntity> applicationUserEntities;

    public UserRoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public UserRoleEntity setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
        return this;
    }

    public List<ApplicationUserEntity> getApplicationUserEntities() {
        return applicationUserEntities;
    }

    public UserRoleEntity setApplicationUserEntities(List<ApplicationUserEntity> applicationUserEntities) {
        this.applicationUserEntities = applicationUserEntities;
        return this;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.roleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity userRole = (UserRoleEntity) o;
        return roleTitle.equals(userRole.roleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleTitle);
    }
}
