package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post", schema = "gcard")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;
    @Column(length = 1000)
    private String content;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;

    public PostEntity() {
    }

    public PostEntity(Integer postId, String title, String content, UserEntity user, BoardEntity board) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.board = board;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
