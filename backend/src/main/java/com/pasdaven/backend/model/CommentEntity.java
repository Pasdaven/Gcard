package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comment", schema = "gcard")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    private String content;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    public CommentEntity() {
    }

    public CommentEntity(Integer messageId, String content, Date time, UserEntity user, PostEntity post) {
        this.messageId = messageId;
        this.content = content;
        this.time = time;
        this.user = user;
        this.post = post;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
