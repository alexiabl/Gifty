package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.gifty.hci.gifty.dao.ProductDao;

public class NotificationsActivity extends AppCompatActivity {

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        NotificationItemFragment notificationItemFragment = new NotificationItemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.notification_items, notificationItemFragment);
        fragmentTransaction.commit();
    }

}
