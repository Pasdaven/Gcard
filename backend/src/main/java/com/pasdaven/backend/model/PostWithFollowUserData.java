package com.pasdaven.backend.model;

public class PostWithFollowUserData {
    PostEntity post;
    int followingCount;
    int fansCount;

    public PostWithFollowUserData(PostEntity post, int followingCount, int fansCount) {
        this.post = post;
        this.followingCount = followingCount;
        this.fansCount = fansCount;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
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
