package com.gifty.hci.gifty;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NotificationsActivity extends AppCompatActivity {


    NotificationsActivity instance = this;

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
                    intent = new Intent(instance, NotificationsActivity.class);
                    menuItem.setChecked(true);
                    break;
                case R.id.nav_profile:
                    intent = new Intent(instance, ProfileActivity.class);
                    break;
            }
            getApplicationContext().startActivity(intent);
            return true;
        }
    };

}
