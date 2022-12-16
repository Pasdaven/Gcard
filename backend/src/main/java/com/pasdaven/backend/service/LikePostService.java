package com.pasdaven.backend.service;

import com.pasdaven.backend.model.LikePostEntity;
import com.pasdaven.backend.repo.LikePostRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LikePostService {

    private final LikePostRepo likePostRepo;

    public LikePostService(LikePostRepo likePostRepo) {
        this.likePostRepo = likePostRepo;
    }

    public void saveLikePost(LikePostEntity likePostEntity) {
        likePostRepo.save(likePostEntity);
    }

    public void deleteLikePost(LikePostEntity likePostEntity) {
        likePostRepo.delete(likePostEntity);
    }
}
