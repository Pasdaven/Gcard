package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccountEntity, Integer> {
}
