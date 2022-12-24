package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "board", schema = "gcard")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;
    private String boardName;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String iconUrl;
    @Column(length = 1000)
    private String apiUrl;

    public BoardEntity() {
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
