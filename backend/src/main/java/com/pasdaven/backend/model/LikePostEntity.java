package com.pasdaven.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "like_post", schema = "gcard")
public class LikePostEntity {
    @EmbeddedId
    private LikePostId id;

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @MapsId("postId")
    private PostEntity post;

    public LikePostEntity() {
    }

    public LikePostEntity(UserEntity user, PostEntity post, LikePostId id) {
        this.user = user;
        this.post = post;
        this.id = id;
    }
    public LikePostId getId() {
        return id;
    }

    public void setId(LikePostId id) {
        this.id = id;
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

    @Embeddable
    public static class LikePostId implements Serializable {
        private Integer userId;
        private Integer postId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getPostId() {
            return postId;
        }

        public void setPostId(Integer postId) {
            this.postId = postId;
        }
    }
}
