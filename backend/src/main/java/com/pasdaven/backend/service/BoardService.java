package com.pasdaven.backend.service;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.repo.BoardRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoardService {

    private final BoardRepo boardRepo;

    public BoardService(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    public BoardEntity saveBoard(BoardEntity boardEntity) {
        return boardRepo.save(boardEntity);
    }
}
