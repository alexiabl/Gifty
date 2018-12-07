package com.gifty.hci.gifty.dao;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gifty.hci.gifty.HomeActivity;
import com.gifty.hci.gifty.model.Product;
import com.gifty.hci.gifty.model.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Alexia Borchgrevink
 * DAO class for Products
 */
public class ProductDao {

    private DatabaseReference dbRef;
    private DatabaseReference productsRef;

    public ProductDao(){
        this.dbRef = FirebaseDatabase.getInstance().getReference();
        this.productsRef = dbRef.child("Products");
    }

    /**
     * Method to obtain all the products from the database
     *
     * @return a list of Product objects
     */
    public List<Product> getAllProducts() {
        //final CountDownLatch done = new CountDownLatch(1);
        final List<Product> products = new ArrayList<>();
        ValueEventListener eventListener = this.productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = (String) data.child("name").getValue();
                    String brand = (String) data.child("brand").getValue();
                    String price = (String) data.child("price").getValue();
                    Long id = (Long) data.child("id").getValue();
                    Long rating = (Long) data.child("rating").getValue();
                    boolean inStock = (Boolean) data.child("inStock").getValue();
                    String imageUrl = (String) data.child("imageUrl").getValue();
                    Product product = new Product(id, name, price, brand, inStock, rating, imageUrl);
                    products.add(product);
                }
                //done.countDown();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //done.await();
        return products;
    }



}
