package com.gifty.hci.gifty.model;

import android.media.Image;
import android.text.format.DateFormat;

import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent the Wishlists users can create in the system
 */
public class Wishlist {

    private String name;
    private Image image;
    private DateFormat deadline;
    private int numItems;
    private boolean expired;
    private List<Product> items;

    public Wishlist(String name, Image image, DateFormat deadline, int numItems, boolean expired) {
        this.name = name;
        this.image = image;
        this.deadline = deadline;
        this.numItems = numItems;
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public DateFormat getDeadline() {
        return deadline;
    }

    public void setDeadline(DateFormat deadline) {
        this.deadline = deadline;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
