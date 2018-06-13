package com.altarosprojects.seriesanimes.utils;

public class CardAnimes {

    private String title;
    private String description;
    private String animeImage;
    private int votes;
    private boolean isSaved;

    public CardAnimes(String title, String description, String animeImage, int votes, boolean isSaved){
        this.title = title;
        this.description = description;
        this.animeImage = animeImage;
        this.votes = votes;
        this.isSaved = isSaved;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAnimeImage() {
        return animeImage;
    }

    public int getVotes() {
        return votes;
    }

    public boolean isSaved() {
        return isSaved;
    }
}
