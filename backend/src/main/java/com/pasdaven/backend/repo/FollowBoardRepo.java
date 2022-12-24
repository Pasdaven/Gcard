package com.pasdaven.backend.repo;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.FollowBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowBoardRepo extends JpaRepository<FollowBoardEntity, Integer> {
    void deleteFollowBoardByBoard(BoardEntity boardById);

    boolean existsByUserAndBoard(UserEntity user, BoardEntity board);
}
