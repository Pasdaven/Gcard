package com.pasdaven.backend.model;

public class FollowUserFollowerData {
    FollowUserEntity followUserEntity;
    int followerCount;
    int followedCount;

    public FollowUserFollowerData(FollowUserEntity followUserEntity, int followerCount, int followedCount) {
        this.followUserEntity = followUserEntity;
        this.followerCount = followerCount;
        this.followedCount = followedCount;
    }

    public FollowUserEntity getFollowUserEntity() {
        return followUserEntity;
    }

    public void setFollowUserEntity(FollowUserEntity followUserEntity) {
        this.followUserEntity = followUserEntity;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(int followedCount) {
        this.followedCount = followedCount;
    }
}
