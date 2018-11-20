package com.gifty.hci.gifty;

import android.app.Activity;
import android.content.Context;
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
import com.gifty.hci.gifty.model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Class for the main Home/Dashboard page
 * @author Alexia Borchgrevink
 */
public class HomeActivity extends AppCompatActivity {

    private ProductDao productDao;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Activity selectedActivity = null;
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    selectedActivity = new HomeActivity();
                    break;
                case R.id.nav_search_friends:
                    //This should be replaced with the activity for Search Friends, not a fragment
                    //selectedActivity = new SearchFriendsFragment();
                    selectedActivity = new SearchFriendsActivity();
                    break;
                case R.id.nav_notifications:
                    //This should be replaced with the activity for Notifications, not a fragment
                    //selectedFragment = new NotificationsFragment();
                    break;
                case R.id.nav_profile:
                    //This should be replaced with the activity for Profile, not a fragment
                    selectedActivity = new ProfileActivity();
                    break;
            }

            //This should switch to the activity selected, not the fragment (When activities are done)
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.productDao = new ProductDao();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        GridView gridView = findViewById(R.id.grid_dashboard_items);
        final ArrayList<Product> products = (ArrayList<Product>) productDao.getAllProducts();
        final ProductGridAdapter productGridAdapter = new ProductGridAdapter(this, (Product[]) products.toArray());
        gridView.setAdapter(productGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product product = products.get(position);
                //book.toggleFavorite()

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                productGridAdapter.notifyDataSetChanged();
            }
        });

    }

    public static class ProductGridAdapter extends BaseAdapter {

        private final Product[] products;
        private final Context context;

        public ProductGridAdapter(Context context, Product[] products){
            this.products = products;
            this.context= context;
        }

        @Override
        public int getCount() {
            return products.length;
        }

        @Override
        public Object getItem(int i) {
            return products[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final Product item = products[i];

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.fragment_product_preview, null);
            }

            // 3
            final ImageView imageView = (ImageView)view.findViewById(R.id.image_product);
            final TextView nameTextView = (TextView)view.findViewById(R.id.text_product_name);
            final TextView brand = (TextView) view.findViewById(R.id.text_brand);

            // 4
            //set product image
            imageView.setImageBitmap(item.getImage());
            nameTextView.setText(item.getName());
            brand.setText(item.getBrand());
           // authorTextView.setText(mContext.getString(book.getAuthor()));

            return view;
        }
    }
}
