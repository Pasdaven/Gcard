package com.pasdaven.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "follow_board", schema = "gcard")
public class FollowBoardEntity {
    @EmbeddedId
    private FollowBoardId id;

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @MapsId("boardId")
    @JsonIgnore
    private BoardEntity board;

    public FollowBoardId getId() {
        return id;
    }

    public void setId(FollowBoardId id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    @Embeddable
    public static class FollowBoardId implements Serializable {
        private Integer userId;
        private Integer boardId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getBoardId() {
            return boardId;
        }

        public void setBoardId(Integer boardId) {
            this.boardId = boardId;
        }
    }
}
