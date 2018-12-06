package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PaymentClosureActivity extends AppCompatActivity {

    public PaymentClosureActivity instance = this;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        Button btn_thankyou = new Button(findViewById(R.id.btn_thankyou));

        btn_thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(instance, HomeActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


    }
}
