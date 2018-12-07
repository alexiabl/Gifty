package com.gifty.hci.gifty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifty.hci.gifty.model.Product;
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
 * Activity for a user's wishlist
 */
public class WishlistActivity extends Activity {

    WishlistActivity instance = this;
    private GridView gridViewProducts;
    private BottomNavigationView bottomNavigationView;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference productsRef = dbRef.child("Products");
    private TextView wishlistLabel;
    private TextView wishlistName;
    private TextView wishlistDeadline;
    private ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        bottomNavigationView = findViewById(R.id.nav_bar);

        backButton = findViewById(R.id.img_top_bar_back);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(WishlistActivity.this, HomeActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_search_friends:
                        Intent intentSearch = new Intent(WishlistActivity.this, SearchFriendsActivity.class);
                        startActivity(intentSearch);
                        break;
                    case R.id.nav_notifications:
                        Intent intentNotifications = new Intent(WishlistActivity.this, NotificationsActivity.class);
                        startActivity(intentNotifications);
                        break;
                    case R.id.nav_profile:
                        Intent intentProfile = new Intent(WishlistActivity.this, ProfileActivity.class);
                        startActivity(intentProfile);
                        break;
                }
                return false;
            }
        });

        this.wishlistLabel = findViewById(R.id.text_top_bar_label);
        this.wishlistName = findViewById(R.id.text_wishlist_name);
        this.wishlistDeadline = findViewById(R.id.text_wishlist_deadline);
        Intent intent = getIntent();

        this.wishlistLabel.setText(intent.getStringExtra("wishlistName"));
        this.wishlistName.setText(intent.getStringExtra("wishlistName"));
        this.wishlistDeadline.setText(intent.getStringExtra("wishlistDeadline"));

        this.gridViewProducts = findViewById(R.id.grid_my_wishlist_items);
        getAllProducts();

        gridViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //productGridAdapter.notifyDataSetChanged();
                //change view to product description activity
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void getAllProducts() {
        this.productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Product> products = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = (String) data.child("name").getValue();
                    String brand = (String) data.child("brand").getValue();
                    String price = (String) data.child("price").getValue();
                    Long id = (Long) data.child("id").getValue();
                    Long rating = (Long) data.child("rating").getValue();
                    boolean inStock = (Boolean) data.child("inStock").getValue();
                    String imageUrl = (String) data.child("imageUrl").getValue();
                    Product product = new Product(id, name, price, brand, inStock, rating, imageUrl);
                    products.add(product);
                }
                WishlistActivity.ProductGridAdapter productGridAdapter = new WishlistActivity.ProductGridAdapter(getApplicationContext(), products);
                gridViewProducts.setAdapter(productGridAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    /**
     * Adapter class for the product items displayed in GridView in the frame layout
     */
    private class ProductGridAdapter extends BaseAdapter {

        private final List<Product> products;
        private final Context context;

        public ProductGridAdapter(Context context, List products) {
            this.products = products;
            this.context = context;
        }

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int i) {
            return products.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final Product item = products.get(i);

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
                view = layoutInflater.inflate(R.layout.fragment_product_preview, viewGroup, false);
            }

            // 3
            final ImageView imageView = (ImageView) view.findViewById(R.id.image_product);
            final TextView nameTextView = (TextView) view.findViewById(R.id.text_product_name);
            final TextView brand = (TextView) view.findViewById(R.id.text_brand);

            //TODO: missing onclick X button to remove item from wishlist + impl in DAO

            // 4
            //set product image
            Picasso.with(context).load(item.getImageUrl()).into(imageView);
            nameTextView.setText(item.getName());
            brand.setText(item.getBrand());
            return view;
        }
    }

}
