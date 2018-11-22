package com.gifty.hci.gifty.dao;

import android.support.annotation.NonNull;

import com.gifty.hci.gifty.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Product> getAllProducts(){
        final List<Product> allProducts = new ArrayList<>();

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String,List<Product>>> genericTypeIndicator = new GenericTypeIndicator<Map<String, List<Product>>>() {};
                Map<String,List<Product>> hashMap = (Map<String, List<Product>>) dataSnapshot.getValue(genericTypeIndicator);
                for (Map.Entry<String,List<Product>> entry: hashMap.entrySet()) {
                    List<Product> products = entry.getValue();
                    for (Product prod: products) {
                        allProducts.add(prod);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return allProducts;
    }



}
