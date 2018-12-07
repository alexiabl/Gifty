package com.gifty.hci.gifty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.dao.UserDao;
import com.gifty.hci.gifty.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Class for the Item Description page
 *
 * @author Shunya Kogure
 */

public class ItemDescriptionActivity extends AppCompatActivity {
    private ProductDao productDao = new ProductDao();
    public ItemDescriptionActivity instance = this;
    private UserDao userDao = new UserDao();
    public FirebaseDatabase database;
    public DatabaseReference ref;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    intent = new Intent(instance, instance.getClass());
                    break;
                case R.id.nav_search_friends:
                    intent = new Intent(instance, SearchFriendsActivity.class);
                    break;
                case R.id.nav_notifications:
                    //intent = new Intent(instance,NotificationsActivity.class);
                    break;
                case R.id.nav_profile:
                    intent = new Intent(instance, ProfileActivity.class);
                    break;
            }
            getApplicationContext().startActivity(intent);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdescription);
        ArrayList<Product> products = (ArrayList<Product>) this.productDao.getAllProducts();
        Product product = products.get(0);
        ref = database.getReference("Users").child("0").child("wishlists").child("0").child("items");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        ImageView product_image = findViewById(R.id.product_image);
        Uri myUri = Uri.parse(product.getImageUrl());
        product_image.setImageURI(myUri);

        Button btn_giftcart = findViewById(R.id.btn_giftcart);

        btn_giftcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(instance, ShoppingCartActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    public void btnInsert(View view) {
        ref.addValueEventListener(new ValueEventListener() {

            public ProductDao productDao1;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> products = (ArrayList<Product>) this.productDao1.getAllProducts();
                String key = ref.push().getKey();
                Product product = products.get(0);
                ref.child(key).setValue(product);
                Toast.makeText(ItemDescriptionActivity.this, "Item added to wishlist", Toast.LENGTH_LONG);
                Intent intent = new Intent(instance, WishlistActivity.class);
                getApplicationContext().startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
