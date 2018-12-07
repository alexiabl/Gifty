package com.gifty.hci.gifty;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.gifty.hci.gifty.model.Product;
import com.gifty.hci.gifty.model.User;
import com.gifty.hci.gifty.model.Wishlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for the search friends navigation functionality
 *
 * @author Alexia Borchgrevink
 */
public class SearchFriendsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public SearchFriendsActivity instance = this;
    private SearchView search_text;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usersRef;
    private GridView gridViewUsers;
    public ImageView imageViewLogo;
    private UsersGridAdapter usersGridAdapter;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<User> usersList;
    private TextView searchResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);
        this.usersRef = dbRef.child("Users");
        this.imageViewLogo = findViewById(R.id.search_gifty_logo);
        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/gifty_logo.png?alt=media&token=b372eabd-9322-4aab-ad6d-49ee6918a559").into(imageViewLogo);
        this.search_text = findViewById(R.id.search_friends_text);
        this.search_text.setOnQueryTextListener(this);
        bottomNavigationView = findViewById(R.id.nav_bar);
        this.searchResults = findViewById(R.id.text_search_results);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(SearchFriendsActivity.this, HomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_search_friends:
                        break;
                    case R.id.nav_notifications:
                        Intent intentNotifications = new Intent(SearchFriendsActivity.this, NotificationsActivity.class);
                        startActivity(intentNotifications);
                        break;
                    case R.id.nav_profile:
                        Intent intentProfile = new Intent(SearchFriendsActivity.this, MyProfileActivity.class);
                        startActivity(intentProfile);
                        break;
            }
                return false;
            }
        });

        this.gridViewUsers = findViewById(R.id.grid_friends);

        this.gridViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                //User user = (User)usersGridAdapter.getItem(i);
                Integer i1 = i;
                String id = i1.toString();
                intent.putExtra("userId", id);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void getUser(final String s) {
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
                Integer count = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = (String) data.child("first_name").getValue();
                    String last_name = (String) data.child("last_name").getValue();
                    Long id = (Long) data.child("id").getValue();
                    if (name.toLowerCase().contains(s.toLowerCase()) || last_name.toLowerCase().contains(s.toLowerCase())) {
                        count++;
                        User user = new User();
                        user.setFirstName(name);
                        user.setLastName(last_name);
                        user.setId(id.toString());
                        user.setImageUrl("https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/user_profilepic%2Fno_profile.jpg?alt=media&token=e0978b7f-5658-462c-9ca7-d48a2688f618");
                        user.setNumWishlists(data.child("wishlists").getChildrenCount());
                        users.add(user);
                    }
                }
                usersGridAdapter = new SearchFriendsActivity.UsersGridAdapter(getApplicationContext(), users);
                searchResults.setText(count.toString() + " results");
                gridViewUsers.setAdapter(usersGridAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        getUser(s);
        search_text.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //getUser(s);
        return false;
    }

    /**
     * Adapter class for the product items displayed in GridView in the frame layout
     */
    public class UsersGridAdapter extends BaseAdapter {

        private final List<User> users;
        private final Context context;

        public UsersGridAdapter(Context context, List users) {
            this.users = users;
            this.context = context;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int i) {
            return users.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final User item = users.get(i);

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
                view = layoutInflater.inflate(R.layout.fragment_user_preview, viewGroup, false);
            }

            // 3
            final ImageView imageView = (ImageView) view.findViewById(R.id.image_search_friends_profile);
            final TextView nameTextView = (TextView) view.findViewById(R.id.text_user_name);
            final TextView numWishlists = (TextView) view.findViewById(R.id.text_num_wishlists);
            final ImageView addFriend = view.findViewById(R.id.image_add_friend);
            // 4

            String defaultProfile = "https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/user_profilepic%2Fno_profile.jpg?alt=media&token=e0978b7f-5658-462c-9ca7-d48a2688f618";
            Picasso.with(context).load(defaultProfile).into(imageView);
            String addFriendImg = "https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/add-friend.png?alt=media&token=f963aaba-3d69-4eb9-b53b-916e2057009d";
            nameTextView.setText(item.getFirstName() + " " + item.getLastName());
            numWishlists.setText(item.getNumWishlists() + " wishlists");
            Picasso.with(context).load(addFriendImg).into(addFriend);

            return view;
        }
    }
}
