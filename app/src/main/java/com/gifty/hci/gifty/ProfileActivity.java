package com.gifty.hci.gifty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
import com.gifty.hci.gifty.model.Product;
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
 * Activity for the user's profile
 *
 * @author Alexia Borchgrevink
 */
public class ProfileActivity extends AppCompatActivity {

    public ProfileActivity instance = this;

    private UserDao userDao = new UserDao();
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = dbRef.child("Users");

    private ListView listView;

    private ImageView profilePicture;
    private TextView userName;
    private TextView numWishlist;
    private TextView numFriends;
    private ImageButton logoutButton;
    private ImageButton settingsButton;

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
                    intent = new Intent(instance, SearchFriendsActivity.class);
                    break;
                case R.id.nav_notifications:
                    intent = new Intent(instance, NotificationsActivity.class);
                    break;
                case R.id.nav_profile:
                    menuItem.setChecked(true);
                    intent = new Intent(instance, this.getClass());
                    break;
            }
            startActivity(intent);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        //get current logged user
        Query query = this.userRef.child("0");
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

        this.listView = findViewById(R.id.profile_wishlist_list);
        this.userName = findViewById(R.id.profile_username);
        this.numWishlist = findViewById(R.id.text_wishlist);
        this.numFriends = findViewById(R.id.text_friends);
        //final ArrayList<Wishlist> wishlists = (ArrayList<Wishlist>) userDao.getUserWishlists("0");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //Wishlist wishlist = wishlists.get(position);
                //productGridAdapter.notifyDataSetChanged();
                //change view to wishlist activity
            }
        });

        ProfileWishlistFragment profileWishlistFragment = new ProfileWishlistFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_user_wishlist, profileWishlistFragment);
        fragmentTransaction.commit();
        //wishlistAdapter.notifyDataSetChanged();

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
        //final CountDownLatch done = new CountDownLatch(1);
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
        //final CountDownLatch done = new CountDownLatch(1);
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
        ProfileActivity.WishlistAdapter wishlistAdapter = new ProfileActivity.WishlistAdapter(this, userWishlists);
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
