package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {
}
