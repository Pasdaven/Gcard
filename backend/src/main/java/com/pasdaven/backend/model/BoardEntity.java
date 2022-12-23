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
    private String iconUrl;
    private String apiUrl;

    public BoardEntity() {
    }

    public BoardEntity(Integer boardId, String boardName, String description, String iconUrl, String apiUrl) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.description = description;
        this.iconUrl = iconUrl;
        this.apiUrl = apiUrl;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getApiUrl() { return apiUrl; }

    public void setApiUrl(String apiUrl) {this.apiUrl = apiUrl;}

}
