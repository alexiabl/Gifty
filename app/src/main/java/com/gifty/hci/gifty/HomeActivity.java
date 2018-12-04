package com.gifty.hci.gifty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for the main Home/Dashboard page
 * @author Alexia Borchgrevink
 */
public class HomeActivity extends AppCompatActivity {

    public HomeActivity instance = this;

    //TODO: solve error with logo image view inflating
    public ImageView imageViewLogo;

    private GridView gridViewProducts;
    private ProductDao productDao = new ProductDao();


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = null;
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    intent = new Intent(instance, instance.getClass());
                    menuItem.setChecked(true);
                    break;
                case R.id.nav_search_friends:
                    intent = new Intent(instance, SearchFriendsActivity.class);
                    break;
                case R.id.nav_notifications:
                    intent = new Intent(instance, NotificationsActivity.class);
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
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        ProductPreviewFragment productPreviewFragment = new ProductPreviewFragment();

        this.gridViewProducts = findViewById(R.id.grid_dashboard_items);
        final ArrayList<Product> products = (ArrayList<Product>) this.productDao.getAllProducts();
        final HomeActivity.ProductGridAdapter productGridAdapter = new HomeActivity.ProductGridAdapter(getApplicationContext(), products);
        gridViewProducts.setAdapter(productGridAdapter);

        //this.productsRef.removeEventListener(eventListener);

        gridViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product product = products.get(position);
                //productGridAdapter.notifyDataSetChanged();
                //change view to product description activity
            }
        });
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.home_dashboard_items, productPreviewFragment);
        fragmentTransaction.commit();
    }

    /**
     * Adapter class for the product items displayed in GridView in the frame layout
     */
    public class ProductGridAdapter extends BaseAdapter {

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

            // 4
            //set product image
            Picasso.with(context).load(item.getImageUrl()).into(imageView);
            nameTextView.setText(item.getName());
            brand.setText(item.getBrand());
            return view;
        }
    }
}
