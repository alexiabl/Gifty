package com.gifty.hci.gifty;

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


/**
 * Class for the main Home/Dashboard page
 */
public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_search_friends:
                    //This should be replaced with the activity for Search Friends, not a fragment
                    selectedFragment = new SearchFriendsFragment();
                    break;
                case R.id.nav_notifications:
                    //This should be replaced with the activity for Notifications, not a fragment
                    selectedFragment = new NotificationsFragment();
                    break;
                case R.id.nav_profile:
                    //This should be replaced with the activity for Profile, not a fragment
                    selectedFragment = new ProfileFragment();
                    break;
            }
            //This should switch to the activity selected, not the fragment (When activities are done)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        if (savedInstanceState == null){
            HomeFragment dashboard = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_home, dashboard).commit();
        }
    }

    //Fragments are included as classes in the activity.
    public static class HomeFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home,container,false);
        }
    }
}
