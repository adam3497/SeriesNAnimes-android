package com.altarosprojects.seriesanimes.utils;

public class CardComment {
    private String username;
    private String commentContent;
    private int likeCount;
    private int dislikeCount;
    private String userProfilePhoto;
    private boolean isLikeActive;
    private boolean isDislikeActive;

    public CardComment(String username, String commentContent, int likeCount, int dislikeCount, String userProfilePhoto, boolean isLikeActive,
                       boolean isDislikeActive){
        this.username = username;
        this.commentContent = commentContent;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.userProfilePhoto = userProfilePhoto;
        this.isLikeActive = isLikeActive;
        this.isDislikeActive = isDislikeActive;

    }


    public String getUsername() {
        return username;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public boolean isLikeActive() {
        return isLikeActive;
    }

    public boolean isDislikeActive() {
        return isDislikeActive;
    }

}
