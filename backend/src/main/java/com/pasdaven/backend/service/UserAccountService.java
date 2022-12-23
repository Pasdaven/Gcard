package com.pasdaven.backend.service;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.repo.UserAccountRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;

    public UserAccountService(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    public UserAccountEntity saveUserAccount(UserAccountEntity userAccountEntity) {
        return userAccountRepo.save(userAccountEntity);
    }

    public UserAccountEntity getUserAccountByEmail(String email) {
        return userAccountRepo.findByEmail(email);
    }

    public List<UserAccountEntity> getAllUserAccount() {
        return userAccountRepo.findAll();
    }

    public UserAccountEntity getUserAccountById(Integer id) {
        return userAccountRepo.findById(id).get();
    }

    public void deleteAllUserAccount() {
        userAccountRepo.deleteAll();
    }
}
