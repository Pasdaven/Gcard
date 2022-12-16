package com.pasdaven.backend.service;

import com.pasdaven.backend.model.LikePostEntity;
import com.pasdaven.backend.repo.LikePostRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

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

    public List<LikePostEntity> getAllLikePosts() {
        return likePostRepo.findAll();
    }
}
