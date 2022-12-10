package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "follow_board", schema = "gcard")
public class FollowBoardEntity {
    @EmbeddedId
    private FollowBoardId id;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("boardId")
    private BoardEntity board;

    @Embeddable
    public class FollowBoardId implements Serializable {
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
