package com.gifty.hci.gifty.model;



import android.widget.ImageView;

import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent product items available
 */

public class Product {

    private String name;

    private double price;

    private String brand;

    private boolean inStock;

    private int rating;

    private ImageView image;

    private List<Review> reviewList;

    public Product(String name, double price, String brand, boolean inStock, int rating) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.inStock = inStock;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
