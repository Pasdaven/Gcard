package com.pasdaven.backend.service;

import com.pasdaven.backend.model.CommentEntity;
import com.pasdaven.backend.repo.CommentRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {

    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public CommentEntity saveComment(CommentEntity comment) {
        return commentRepo.save(comment);
    }

    public CommentEntity getCommentById(int commentId) {
        return commentRepo.findById(commentId).get();
    }

    public void deleteComment(int commentId) {
        commentRepo.deleteById(commentId);
    }
}
