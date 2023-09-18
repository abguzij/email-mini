package com.example.practice.service.impl;

import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.repository.ApplicationUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserEntityRepository applicationUserEntityRepository;

    @Autowired
    public ApplicationUserDetailsService(
            ApplicationUserEntityRepository applicationUserEntityRepository
    ) {
        this.applicationUserEntityRepository = applicationUserEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUserEntity> optionalApplicationUser =
                this.applicationUserEntityRepository.getApplicationUserEntityByUsername(username);
        if(optionalApplicationUser.isPresent()) {
            return optionalApplicationUser.get();
        }
        throw new UsernameNotFoundException("Пользователя с таким именем не существует в системе!");
    }

    public ApplicationUserEntity getUserById(Long id) {
        return this.applicationUserEntityRepository.getReferenceById(id);
    }
}
