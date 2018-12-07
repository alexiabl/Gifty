package com.gifty.hci.gifty.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent the Wishlists users can create in the system
 */
public class Wishlist {

    private String name;
    private String imageUrl;
    private String deadline;
    private Long numItems;
    private boolean expired;
    private List<Product> items;
    private Long id;

    public Wishlist(String name, String deadline, Long numItems, boolean expired) {
        this.name = name;
        this.deadline = deadline;
        this.numItems = numItems;
        this.expired = expired;
    }

    public Wishlist(Parcel in) {

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Wishlist createFromParcel(Parcel in) {
            return new Wishlist(in);
        }

        public Wishlist[] newArray(int size) {
            return new Wishlist[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image) {
        this.imageUrl = image;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Long getNumItems() {
        return numItems;
    }

    public void setNumItems(Long numItems) {
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
