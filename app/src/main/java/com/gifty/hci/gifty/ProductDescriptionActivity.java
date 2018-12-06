package com.gifty.hci.gifty;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gifty.hci.gifty.dao.ProductDao;

/**
 * Class for the Item Description page
 *
 * @author Shunya Kogure
 */

public class ProductDescriptionActivity extends AppCompatActivity {
    private ProductDao productDao;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Activity selectedActivity = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedActivity = new com.gifty.hci.gifty.HomeActivity();
                    break;
                case R.id.nav_search_friends:
                    //This should be replaced with the activity for Search Friends, not a fragment
                    //selectedActivity = new SearchFriendsFragment();
                    selectedActivity = new SearchFriendsActivity();
                    break;
                case R.id.nav_notifications:
                    //This should be replaced with the activity for Notifications, not a fragment
                    //selectedFragment = new NotificationsFragment();
                    break;
                case R.id.nav_profile:
                    //This should be replaced with the activity for Profile, not a fragment
                    selectedActivity = new ProfileActivity();
                    break;
            }
            //This should switch to the activity selected, not the fragment (When activities are done)
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdescription);
        this.productDao = new ProductDao();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        View product_image = findViewById(R.id.product_image);

        Button btn_wishlist = (Button) findViewById(R.id.btn_wishllist);
        Button btn_giftcart = (Button) findViewById(R.id.btn_giftcart);

        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_giftcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartActivity giftcartActivity = new ShoppingCartActivity();
            }
        });


    }
}
