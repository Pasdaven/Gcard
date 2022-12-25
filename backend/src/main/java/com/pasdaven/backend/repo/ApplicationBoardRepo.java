package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pasdaven.backend.model.ApplicationBoardEntity.Status;

import java.util.List;

@Repository
public interface ApplicationBoardRepo extends JpaRepository<ApplicationBoardEntity, Integer> {
  List<ApplicationBoardEntity> findByState(Status state);
}
