package com.pasdaven.backend.service;

import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.repo.UserRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

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

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users;
    }

    public UserEntity getUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    public List<UserEntity> searchUserByName(String keyword) {
        return userRepo.findByUserNameContaining(keyword);
    }
}
