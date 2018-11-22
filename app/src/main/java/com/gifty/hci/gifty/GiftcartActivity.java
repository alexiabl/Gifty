package com.gifty.hci.gifty;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the GiftCart page page
 *
 * @author Shunya Kogure
 */
public class GiftcartActivity extends AppCompatActivity {
    private ProductDao productDao;
    ListView gifts;
    private static final String[] cart = {"Protein", "My Hero Academia"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftcart);
        this.productDao = new ProductDao();
        final ArrayList<Product> products = (ArrayList<Product>) productDao.getAllProducts();
        final MyAdapter myAdapter = new MyAdapter(this, products);
        gifts = findViewById(R.id.gifts);
        gifts.setAdapter(myAdapter);

    }

    class MyAdapter extends BaseAdapter {
        List<Product> giftcart;
        Context context;
        LayoutInflater layoutInflater = null;

        MyAdapter(Context context, List<Product> giftcart) {
            this.giftcart = giftcart;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return giftcart.size();
        }

        @Override
        public Object getItem(int i) {
            return giftcart.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.fragment_giftcart, parent, false);

            //Use Picasso for image view
            ((ImageView) convertView.findViewById(R.id.image_product)).setImageBitmap(giftcart.get(i).getImage());
            ((TextView) convertView.findViewById(R.id.text_product_name)).setText(giftcart.get(i).getName());
            ((TextView) convertView.findViewById(R.id.text_price)).setText(String.valueOf(giftcart.get(i).getPrice()));
            ((TextView) convertView.findViewById(R.id.text_brand)).setText(String.valueOf(giftcart.get(i).getBrand()));
            if (giftcart.get(i).isInStock()) {
                ((TextView) convertView.findViewById(R.id.text_quantity)).setText(String.valueOf("In stock"));
            } else {
                ((TextView) convertView.findViewById(R.id.text_quantity)).setText(String.valueOf("Out of Stock"));
            }

            return convertView;
        }
    }
}
