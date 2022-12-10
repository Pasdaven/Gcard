package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "like_post", schema = "gcard")
public class LikePostEntity {
    @EmbeddedId
    private LikePostId id;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("postId")
    private PostEntity post;

    @Embeddable
    public class LikePostId implements Serializable {
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
