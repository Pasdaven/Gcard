package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Integer> {
}
