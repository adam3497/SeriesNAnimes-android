package com.altarosprojects.seriesanimes.utils;

/**
 * Created by labexp on 08/06/18.
 */

public class CardReviews {
    private String userIcon;
    private String title;
    private String description;
    private String username;
    private String type;
    private String name;
    private String publicationDate;

    public CardReviews(String userIcon, String title, String description, String username, String type, String name, String publicationDate){
        this.userIcon = userIcon;
        this.title = title;
        this.description = description;
        this.username = username;
        this.type = type;
        this.name = name;
        this.publicationDate = publicationDate;
    }


    public String getUserIcon() {
        return userIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
