package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    BaseActivity baseActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        intent = new Intent(baseActivity, HomeActivity.class);
                        menuItem.setChecked(true);
                        break;
                    case R.id.nav_search_friends:
                        intent = new Intent(baseActivity, SearchFriendsActivity.class);
                        break;
                    case R.id.nav_notifications:
                        intent = new Intent(baseActivity, NotificationsActivity.class);
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(baseActivity, ProfileActivity.class);
                        break;
                }
                getApplicationContext().startActivity(intent);
                return true;
            }
        };
    }

}
