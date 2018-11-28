package com.gifty.hci.gifty;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemFragment extends Fragment {


    private ShoppingCartItemFragment.OnFragmentInteractionListener mListener;

    private ProductDao productDao;

    public ShoppingCartItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_shoppingcart, null);
        ListView listView = view.findViewById(R.id.gifts);
        final ArrayList<Product> products = (ArrayList<Product>) this.productDao.getAllProducts();
        final ShoppingCartItemFragment.MyAdapter myAdapter = new MyAdapter(getActivity(), products);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product product = products.get(position);
                myAdapter.notifyDataSetChanged();
                //change view to product description
            }
        });
        return view;

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class MyAdapter extends BaseAdapter {
        List<Product> giftcart;
        Context context;
        LayoutInflater layoutInflater = null;

        public MyAdapter(Context context, List<Product> giftcart) {
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
            convertView = layoutInflater.inflate(R.layout.fragment_shoppingcart, parent, false);

            //Use Picasso for image view

//            ((ImageView) convertView.findViewById(R.id.image_product)).setImageBitmap(giftcart.get(i).getImageUrl());
            final TextView textView = convertView.findViewById(R.id.text_product_name);
            final TextView textView1 = convertView.findViewById(R.id.text_price);
            final TextView textView2 = convertView.findViewById(R.id.text_brand);

            if (giftcart.get(i).isInStock()) {
                TextView textView3 = convertView.findViewById(R.id.text_quantity);
                textView3.setText(String.valueOf("In stock"));
            } else {
                TextView textView3 = convertView.findViewById(R.id.text_quantity);
                textView3.setText(String.valueOf("Out of stock"));
            }

            textView.setText(giftcart.get(i).getName());
            textView1.setText(String.valueOf(giftcart.get(i).getPrice()));
            textView2.setText(String.valueOf(giftcart.get(i).getBrand()));

            return convertView;
        }
    }


}
