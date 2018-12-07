package com.gifty.hci.gifty.model;

import android.text.format.DateFormat;

/**
 * @author Alexia Borchgrevink
 * Class to represent Product Review's users write
 */
public class Review {

    private String title;
    private String description;
    private String date;
    private Long rating;
    private Long id;

    public Review(String title, String description, String date, Long rating, Long id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.rating = rating;
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
