package com.pasdaven.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "follow_user", schema = "gcard")
public class FollowUserEntity {
    @EmbeddedId
    private FollowUserId id;

    @ManyToOne
    @MapsId("followerId")
    @JsonIgnore
    private UserEntity follower;

    @ManyToOne
    @MapsId("followedId")
    private UserEntity followed;

    public FollowUserEntity() {
    }

    public FollowUserEntity(FollowUserId id, UserEntity follower, UserEntity followed) {
        this.id = id;
        this.follower = follower;
        this.followed = followed;
    }

    public FollowUserId getId() {
        return id;
    }

    public void setId(FollowUserId id) {
        this.id = id;
    }

    public UserEntity getFollower() {
        return follower;
    }

    public void setFollower(UserEntity follower) {
        this.follower = follower;
    }

    public UserEntity getFollowed() {
        return followed;
    }

    public void setFollowed(UserEntity followed) {
        this.followed = followed;
    }

    @Embeddable
    public static class FollowUserId implements Serializable {
        private Integer followerId;
        private Integer followedId;

        public Integer getFollowerId() {
            return followerId;
        }

        public void setFollowerId(Integer followerId) {
            this.followerId = followerId;
        }

        public Integer getFollowedId() {
            return followedId;
        }

        public void setFollowedId(Integer followedId) {
            this.followedId = followedId;
        }
    }
}

