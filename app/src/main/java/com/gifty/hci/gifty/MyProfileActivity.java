package com.gifty.hci.gifty;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.UserDao;
import com.gifty.hci.gifty.model.User;
import com.gifty.hci.gifty.model.Wishlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for Logged in user profile
 *
 * @author alexiaborchgrevink
 */
public class MyProfileActivity extends AppCompatActivity {

    public MyProfileActivity instance = this;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = dbRef.child("Users");
    private User currentUser;

    private ListView listView;

    private ImageView profilePicture;
    private TextView userName;
    private TextView numWishlist;
    private TextView numFriends;
    private ImageButton logoutButton;
    private ImageButton settingsButton;
    private BottomNavigationView bottomNavigationView;
    private WishlistAdapter wishlistAdapter;
    private String userId;
    private ImageButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        this.bottomNavigationView = findViewById(R.id.nav_bar);
        //this.wishlistAdapter = new ProfileActivity.WishlistAdapter(this);


        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(MyProfileActivity.this, HomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_search_friends:
                        Intent intentSearch = new Intent(MyProfileActivity.this, SearchFriendsActivity.class);
                        startActivity(intentSearch);
                        break;
                    case R.id.nav_notifications:
                        Intent intentNotifications = new Intent(MyProfileActivity.this, NotificationsActivity.class);
                        startActivity(intentNotifications);
                        break;
                    case R.id.nav_profile:
                        break;
                }
                return false;
            }
        });

        //TODO: get current logged user

        //Query query = this.userRef.child(currentUser.getId().toString());
        Query query = this.userRef.child("3");
        ValueEventListener eventListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getBasicUserData(dataSnapshot);
                DataSnapshot wishlists = dataSnapshot.child("wishlists");
                getUserWishlists(wishlists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        this.listView = findViewById(R.id.my_profile_wishlist_list);
        this.userName = findViewById(R.id.my_profile_username);
        this.numWishlist = findViewById(R.id.text_my_wishlist);
        this.numFriends = findViewById(R.id.text_my_friends);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MyProfileWishlistActivity.class);
                Wishlist wishlist = (Wishlist) wishlistAdapter.getItem(position);
                intent.putExtra("wishlistName", wishlist.getName());
                intent.putExtra("wishlistDeadline", wishlist.getDeadline());
                startActivity(intent);
            }
        });

        logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: logout
            }
        });

        settingsButton = findViewById(R.id.btn_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                // change to edit profile activity for user
            }
        });

        createButton = findViewById(R.id.icon_create_wishlist);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(instance, CreateWishlistActivity.class);
                intent.putExtra("userId", "3");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void getBasicUserData(@NotNull DataSnapshot dataSnapshot) {
        User user = new User();
        String first_name = (String) dataSnapshot.child("first_name").getValue();
        String last_name = (String) dataSnapshot.child("last_name").getValue();
        DataSnapshot followers = dataSnapshot.child("followers");
        user.setNumFollowers(followers.getChildrenCount());
        DataSnapshot wishlists = dataSnapshot.child("wishlists");
        user.setNumWishlists(wishlists.getChildrenCount());
        this.userName.setText(first_name + " " + last_name);
        this.numFriends.setText(user.getNumFollowers().toString() + " Followers");
        this.numWishlist.setText(user.getNumWishlists().toString() + " Wishlists");
    }

    public void getUserWishlists(@NotNull DataSnapshot dataSnapshot) {
        final List<Wishlist> userWishlists = new ArrayList<>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            String deadline = (String) data.child("deadline").getValue();
            String name = (String) data.child("name").getValue();
            Long numItems = (Long) data.child("numItems").getValue();
            boolean expired = (Boolean) data.child("expired").getValue();
            String imageUrl = (String) data.child("imageUrl").getValue();
            Wishlist wishlist = new Wishlist(name, deadline, numItems, expired);
            userWishlists.add(wishlist);
        }
        wishlistAdapter = new MyProfileActivity.WishlistAdapter(this, userWishlists);
        listView.setAdapter(wishlistAdapter);
    }

    /**
     * Adapter class for the product items displayed in GridView in the frame layout
     */
    public class WishlistAdapter extends BaseAdapter {

        private final List<Wishlist> wishlists;
        private final Context context;

        public WishlistAdapter(Context context, List products) {
            this.wishlists = products;
            this.context = context;
        }

        public WishlistAdapter(Context context) {
            wishlists = new ArrayList<>();
            this.context = context;
        }

        @Override
        public int getCount() {
            return wishlists.size();
        }

        @Override
        public Object getItem(int i) {
            return wishlists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final Wishlist item = wishlists.get(i);

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
                view = layoutInflater.inflate(R.layout.fragment_profile_wishlist, viewGroup, false);
            }

            // 3
            final ImageView imageView = (ImageView) view.findViewById(R.id.image_wishlist_preview);
            final TextView name = (TextView) view.findViewById(R.id.text_wishpreview_name);
            final TextView numItems = (TextView) view.findViewById(R.id.text_wishpreview_items);
            final TextView deadline = (TextView) view.findViewById(R.id.text_wishpreview_deadline);


            // 4
            //set product image
            String defaultImgUrl = "https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/product_images%2Fxmas.jpg?alt=media&token=6f7290f6-4f46-4e78-8f50-7b358636b02c";
            Picasso.with(context).load(defaultImgUrl).into(imageView);
            name.setText(item.getName());
            numItems.setText(item.getNumItems().toString() + " items");
            deadline.setText("Deadline: " + item.getDeadline());

            return view;
        }


    }
}
