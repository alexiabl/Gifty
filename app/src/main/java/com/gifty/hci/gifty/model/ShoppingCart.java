package com.gifty.hci.gifty.model;

import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent each user's shopping cart
 */
public class ShoppingCart {

    private List<Product> items;
    private double totalAmount;

    public ShoppingCart(List<Product> products, double totalAmount){
        this.items = products;
        this.totalAmount = totalAmount;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
