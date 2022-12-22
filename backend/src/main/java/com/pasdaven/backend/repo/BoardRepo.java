package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<BoardEntity, Integer> {
    List<BoardEntity> findByBoardNameContaining(String boardName);
}
