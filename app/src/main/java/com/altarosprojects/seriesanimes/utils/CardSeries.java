package com.altarosprojects.seriesanimes.utils;

public class CardSeries {
    private String title;
    private String description;
    private String serieImage;
    private int votes;
    private Boolean isSaved;

    public CardSeries(String title, String description, String serieImage, int votes, Boolean isSaved){
        this.title = title;
        this.description = description;
        this.serieImage = serieImage;
        this.votes = votes;
        this.isSaved = isSaved;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSerieImage() {
        return serieImage;
    }

    public int getVotes() {
        return votes;
    }

    public Boolean getSaved() {
        return isSaved;
    }
}
