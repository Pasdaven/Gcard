package com.pasdaven.backend.service;

import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.repo.UserRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }
}
