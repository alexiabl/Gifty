package com.gifty.hci.gifty.model;

import android.text.format.DateFormat;

import java.util.List;

/**
 * @author Alexia Borchgrevink
 * Class to represent the users accounts in the system
 */
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private DateFormat birthdate;
    private String password;
    private List<User> followers;
    private List<User> following;
    private ShoppingCart shoppingCart;

    public User(String email, String firstName, String lastName, DateFormat birthdate, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateFormat getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DateFormat birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
