package com.gifty.hci.gifty.model;


import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent product items available
 */

public class Product {

    private String name;

    private String price;

    private String brand;

    private boolean inStock;

    private Long rating;

    private String imageUrl;

    private List<Review> reviewList;

    public Product(String name, String price, String brand, boolean inStock, Long rating, List<Review> reviewList, String imageUrl) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.inStock = inStock;
        this.rating = rating;
        this.reviewList = reviewList;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image) {
        this.imageUrl = image;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
