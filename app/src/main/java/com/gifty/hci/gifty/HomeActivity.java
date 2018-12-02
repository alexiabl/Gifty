package com.gifty.hci.gifty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Class for the main Home/Dashboard page
 * @author Alexia Borchgrevink
 */
public class HomeActivity extends AppCompatActivity {

    public HomeActivity instance = this;


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = null;
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    intent = new Intent(instance, instance.getClass());
                    menuItem.setChecked(true);
                    break;
                case R.id.nav_search_friends:
                    intent = new Intent(instance, SearchFriendsActivity.class);
                    break;
                case R.id.nav_notifications:
                    intent = new Intent(instance, NotificationsActivity.class);
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
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        ProductPreviewFragment productPreviewFragment = new ProductPreviewFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_dashboard_items, productPreviewFragment);
        fragmentTransaction.commit();
    }

}
