package com.pasdaven.backend.service;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.repo.PostRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
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

    public PostEntity getPostById(Integer postId) {
        return postRepo.findById(postId).get();
    }

    public void deletePostById(Integer id) {
        postRepo.deleteById(id);
    }

    public List<PostEntity> getPostsByKeyword(String keyword) {
        return postRepo.findByContentContainingOrTitleContaining(keyword, keyword);
    }


}
