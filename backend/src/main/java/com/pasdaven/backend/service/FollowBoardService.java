package com.pasdaven.backend.service;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.FollowBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.repo.FollowBoardRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowBoardService {

    private final FollowBoardRepo followBoardRepo;

    public FollowBoardService(FollowBoardRepo followBoardRepo) {
        this.followBoardRepo = followBoardRepo;
    }

    public void saveFollowBoard(FollowBoardEntity followBoardEntity) {
        followBoardRepo.save(followBoardEntity);
    }

    public List<FollowBoardEntity> getAllFollowBoard() {
        return followBoardRepo.findAll();
    }
    
    public void deleteFollowBoard(FollowBoardEntity followBoardEntity) {
        followBoardRepo.delete(followBoardEntity);
    }

    public void deleteAllFollowBoard() {
        followBoardRepo.deleteAll();
    }

    public void deleteFollowBoardByBoard(BoardEntity boardById) {
        followBoardRepo.deleteFollowBoardByBoard(boardById);
    }

    public boolean checkFollowBoard(FollowBoardEntity followBoardEntity) {
        return followBoardRepo.existsByUserAndBoard(followBoardEntity.getUser(), followBoardEntity.getBoard());

    }
}
