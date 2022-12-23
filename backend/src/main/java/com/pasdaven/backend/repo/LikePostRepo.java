package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.LikePostEntity;
import com.pasdaven.backend.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepo extends JpaRepository<LikePostEntity, Integer> {
    void deleteAllByPost(PostEntity post);
}
