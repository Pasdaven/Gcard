package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.CommentEntity;
import com.pasdaven.backend.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByPost(PostEntity post);

    void deleteAllByPost(PostEntity post);
}
