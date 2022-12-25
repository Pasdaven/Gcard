package com.pasdaven.backend.service;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import com.pasdaven.backend.repo.ApplicationBoardRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.pasdaven.backend.model.ApplicationBoardEntity.Status;

import java.util.List;

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

    public List<ApplicationBoardEntity> getAllApplicationBoard() {
        return applicationBoardRepo.findAll();
    }

    public List<ApplicationBoardEntity> getPendingApplicationBoard() {
        return applicationBoardRepo.findByState(Status.pending);
    }

    public ApplicationBoardEntity getApplicationBoardById(Integer applicationId) {
        return applicationBoardRepo.findById(applicationId).get();
    }

//    public void deleteAllApplicationBoard() {
//        applicationBoardRepo.deleteAll();
//    }
}
