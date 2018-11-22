package com.gifty.hci.gifty;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Activity for the search friends navigation functionality
 *
 * @author Alexia Borchgrevink
 */
public class SearchFriendsActivity extends AppCompatActivity {

    public SearchFriendsActivity instance = this;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    intent = new Intent(instance, HomeActivity.class);
                    break;
                case R.id.nav_search_friends:
                    intent = new Intent(instance, instance.getClass());
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
        setContentView(R.layout.activity_search_friends);
        BottomNavigationView navigation = findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        UserPreviewFragment userPreviewFragment = new UserPreviewFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.search_friends_results, userPreviewFragment);
        fragmentTransaction.commit();


    }
}
