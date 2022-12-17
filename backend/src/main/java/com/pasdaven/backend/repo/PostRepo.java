package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findAllByUser(UserEntity user);

    List<PostEntity> findByContentContainingOrTitleContaining(String content, String title);

}
