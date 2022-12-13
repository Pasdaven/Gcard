package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post", schema = "gcard")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String content;
    private Integer score;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;

    public PostEntity() {
    }

    public PostEntity(Integer postId, String content, Integer score, UserEntity user, BoardEntity board) {
        this.postId = postId;
        this.content = content;
        this.score = score;
        this.user = user;
        this.board = board;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
}
