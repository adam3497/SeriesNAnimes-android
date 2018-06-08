package com.altarosprojects.seriesanimes.utils;

/**
 * Created by labexp on 08/06/18.
 */

public class Card {
    private long id;
    private String name;
    private int color;

    public Card(long id, String name, int color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
