package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "application_board", schema = "gcard")
public class ApplicationBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;
    @Column(length = 1000)
    private String apiUrl;
    private String boardName;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String iconUrl;

    public enum Status {
        pending, approved, rejected
    }

    private Status state;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private UserEntity admin;

    public ApplicationBoardEntity() {
    }

    public Integer getApplicationId() {
        return applicationId;
    }

//    public void setApplicationId(Integer applicationId) {
//        this.applicationId = applicationId;
//    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
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

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }
}
