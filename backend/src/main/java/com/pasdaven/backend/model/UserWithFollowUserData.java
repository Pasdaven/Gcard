package com.pasdaven.backend.model;

public class UserWithFollowUserData {
    UserEntity user;
    int followingCount;
    int fansCount;

    public UserWithFollowUserData() {
    }

    public UserWithFollowUserData(UserEntity user, int followingCount, int fansCount) {
        this.user = user;
        this.followingCount = followingCount;
        this.fansCount = fansCount;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }
}
