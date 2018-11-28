package com.gifty.hci.gifty;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gifty.hci.gifty.dao.ProductDao;

/**
 * Class for the GiftCart page page
 *
 * @author Shunya Kogure
 */
public class ShoppingCartActivity extends AppCompatActivity {
    private ProductDao productDao;
    ListView gifts;
    private static final String[] cart = {"Protein", "My Hero Academia"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShoppingCartItemFragment shoppingCartItemFragment = new ShoppingCartItemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.shopping_cart_items, shoppingCartItemFragment);
        fragmentTransaction.commit();
    }
}
