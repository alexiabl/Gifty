package com.gifty.hci.gifty.dao;

import android.support.annotation.NonNull;

import com.gifty.hci.gifty.model.Product;
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
        Query query = this.productsRef.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Product product = data.getValue(Product.class);
                    allProducts.add(product);
                    System.out.println(product.getName() + " " + product.getPrice());
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
        return allProducts;
    }



}
