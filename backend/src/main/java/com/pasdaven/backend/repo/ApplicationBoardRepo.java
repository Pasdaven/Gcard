package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationBoardRepo extends JpaRepository<ApplicationBoardEntity, Integer> {
}
