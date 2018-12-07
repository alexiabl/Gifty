package com.gifty.hci.gifty.dao;

import android.support.annotation.NonNull;

import com.gifty.hci.gifty.model.Product;
import com.gifty.hci.gifty.model.User;
import com.gifty.hci.gifty.model.Wishlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserDao {

    private DatabaseReference dbRef;
    private DatabaseReference usersRef;

    public UserDao() {
        this.dbRef = FirebaseDatabase.getInstance().getReference();
        this.usersRef = dbRef.child("Users");
    }

    public User getBasicUserData(String id) {
        //final CountDownLatch done = new CountDownLatch(1);
        final User user = new User();
        Query query = this.usersRef.child("/" + id);
        ValueEventListener eventListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String first_name = (String) dataSnapshot.child("first_name").getValue();
                String last_name = (String) dataSnapshot.child("last_name").getValue();
                user.setFirstName(first_name);
                user.setLastName(last_name);
                DataSnapshot followers = dataSnapshot.child("followers");
                user.setNumFollowers(followers.getChildrenCount());
                DataSnapshot wishlists = dataSnapshot.child("wishlists");
                user.setNumWishlists(wishlists.getChildrenCount());
                //done.countDown();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //done.await();
        return user;

    }

    public List<Wishlist> getUserWishlists(String id) {
        //final CountDownLatch done = new CountDownLatch(1);
        final List<Wishlist> userWishlists = new ArrayList<>();
        Query query = this.usersRef.child("/" + id + "/wishlists");
        ValueEventListener eventListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String deadline = (String) data.child("deadline").getValue();
                    String name = (String) data.child("name").getValue();
                    Long numItems = (Long) data.child("numItems").getValue();
                    boolean expired = (Boolean) data.child("expired").getValue();
                    String imageUrl = (String) data.child("imageUrl").getValue();
                    Wishlist wishlist = new Wishlist(name, deadline, numItems, expired);
                    userWishlists.add(wishlist);
                }
                // done.countDown();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //done.await();
        return userWishlists;
    }

}
