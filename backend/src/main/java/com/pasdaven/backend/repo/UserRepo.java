package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByUserNameContaining(String keyword);
}
