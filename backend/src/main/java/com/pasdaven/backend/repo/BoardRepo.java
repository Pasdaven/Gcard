package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<BoardEntity, Integer> {
}
