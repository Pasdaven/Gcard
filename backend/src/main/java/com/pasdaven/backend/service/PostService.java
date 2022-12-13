package com.pasdaven.backend.service;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.repo.PostRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public PostEntity savePost(PostEntity postEntity) {
        return postRepo.save(postEntity);
    }

    public Optional<PostEntity> getPostById(Integer postId) {
        return postRepo.findById(postId);
    }
}
