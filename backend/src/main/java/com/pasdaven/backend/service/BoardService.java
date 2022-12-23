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

    public void deleteBoardById(Integer boardId) {
        boardRepo.deleteById(boardId);
    }

    public void deleteAllBoards() {
        boardRepo.deleteAll();
    }

    public List<BoardEntity> searchBoardByName(String boardName) {
        return boardRepo.findByBoardNameContaining(boardName);
    }

    public BoardEntity getBoardByBoardName(String symbol) {
        return boardRepo.findByBoardName(symbol);
    }
}
