package com.example.practice.service;

import com.example.practice.entity.ApplicationUserEntity;

import java.util.List;

public interface ApplicationUserService {
    List<ApplicationUserEntity> getExistingApplicationUsersByEmails(List<String> emails);
}
