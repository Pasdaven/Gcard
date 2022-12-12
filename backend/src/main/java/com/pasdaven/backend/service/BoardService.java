package com.pasdaven.backend.service;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.repo.BoardRepo;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

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

    public List<BoardEntity> getAllBoards() {
        return boardRepo.findAll();
    }

    public BoardEntity getBoardById(Integer boardId) {
        return boardRepo.findById(boardId).get();
    }
}
