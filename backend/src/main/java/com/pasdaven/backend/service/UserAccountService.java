package com.pasdaven.backend.service;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.repo.UserAccountRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
}
