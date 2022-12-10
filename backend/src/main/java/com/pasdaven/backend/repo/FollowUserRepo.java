package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.FollowUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUserRepo extends JpaRepository<FollowUserEntity, Integer> {
}
