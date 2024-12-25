package com.example.cineplexapp;

public class Movie {
    private String title;
    private String showDate;
    private int imageResource;
    private boolean isSelected;

    public Movie(String title, String showDate, int imageResource) {
        this.title = title;
        this.showDate = showDate;
        this.imageResource = imageResource;
        this.isSelected = false;
    }

    public String getTitle() {
        return title;
    }

    public String getShowDate() {
        return showDate;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
