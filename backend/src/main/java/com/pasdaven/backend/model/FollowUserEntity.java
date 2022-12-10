package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "follow_user", schema = "gcard")
public class FollowUserEntity {
    @EmbeddedId
    private FollowUserId id;

    @ManyToOne
    @MapsId("followerId")
    private UserEntity follower;

    @ManyToOne
    @MapsId("followedId")
    private UserEntity followed;

    @Embeddable
    public class FollowUserId implements Serializable {
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

