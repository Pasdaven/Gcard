package com.pasdaven.backend.service;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.repo.PostRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
}
