package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "board", schema = "gcard")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;
    private String boardName;
    private String description;

    public BoardEntity() {
    }

    public BoardEntity(Integer boardId, String boardName, String description) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.description = description;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
