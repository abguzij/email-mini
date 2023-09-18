package com.example.practice.service.impl;

import com.example.practice.entity.ApplicationUserEntity;
import com.example.practice.repository.ApplicationUserEntityRepository;
import com.example.practice.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserEntityRepository applicationUserEntityRepository;

    @Autowired
    public ApplicationUserServiceImpl(
            ApplicationUserEntityRepository applicationUserEntityRepository
    ) {
        this.applicationUserEntityRepository = applicationUserEntityRepository;
    }

    @Override
    public List<ApplicationUserEntity> getExistingApplicationUsersByEmails(List<String> emails) {
        List<ApplicationUserEntity> applicationUserEntities = new ArrayList<>();
        for (String email : emails) {
            Optional<ApplicationUserEntity> userEntityOptional =
                    this.applicationUserEntityRepository.getApplicationUserEntityByEmail(email);
            userEntityOptional.ifPresent(applicationUserEntities::add);
        }
        return applicationUserEntities;
    }
}
