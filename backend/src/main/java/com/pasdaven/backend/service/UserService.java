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

    public UserEntity getUser(Integer id) {
        return userRepo.findById(id).get();
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }

    public List<UserEntity> listAllUser() {
        return userRepo.findAll();
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

}
