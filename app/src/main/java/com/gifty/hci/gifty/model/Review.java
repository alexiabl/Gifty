package com.gifty.hci.gifty.model;

import android.text.format.DateFormat;

/**
 * @author Alexia Borchgrevink
 * Class to represent Product Review's users write
 */
public class Review {

    private String title;
    private String description;
    private DateFormat date;
    private int rating;

    public Review(String title, String description, DateFormat date, int rating) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
