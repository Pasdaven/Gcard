package com.pasdaven.backend.service;

import com.pasdaven.backend.model.FollowUserEntity;
import com.pasdaven.backend.repo.FollowUserRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FollowUserService {

    private final FollowUserRepo followUserRepo;

    public FollowUserService(FollowUserRepo followUserRepo) {
        this.followUserRepo = followUserRepo;
    }
}
