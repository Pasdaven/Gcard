package com.pasdaven.backend.repo;


import com.pasdaven.backend.model.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<ManagerEntity, Integer> {
}

