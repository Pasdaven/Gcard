package com.pasdaven.backend.service;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import com.pasdaven.backend.repo.ApplicationBoardRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplicationBoardService {
    final ApplicationBoardRepo applicationBoardRepo;

    public ApplicationBoardService(ApplicationBoardRepo applicationBoardRepo) {
        this.applicationBoardRepo = applicationBoardRepo;
    }

    public void saveApplicationBoard(ApplicationBoardEntity applicationBoardEntity) {
        applicationBoardRepo.save(applicationBoardEntity);
    }
}
