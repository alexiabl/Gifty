package com.gifty.hci.gifty;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.UserDao;
import com.gifty.hci.gifty.model.Product;
import com.gifty.hci.gifty.model.User;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemFragment extends Fragment {

    private ShoppingCartItemFragment.OnFragmentInteractionListener mListener;

    private UserDao userDao = new UserDao();

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
        final ArrayList<User> users = (ArrayList<User>) this.userDao.getAllUsers();
        final ShoppingCartItemFragment.MyAdapter myAdapter = new MyAdapter(getActivity(), users);
        listView.setAdapter(myAdapter);

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

        public MyAdapter(Context context, List<User> users) {
            this.giftcart = users.get(0).getShoppingCart().getItems();
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
        public View getView(int i, View view, ViewGroup parent) {

            final Product item = giftcart.get(i);

            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.fragment_shoppingcart, null);
            }

            final ImageView imageView = view.findViewById(R.id.image_product);
            final TextView nametextView = view.findViewById(R.id.text_product_name);
            final TextView gifted_to = view.findViewById(R.id.text_gift_to);
            final TextView quantity = view.findViewById(R.id.text_quantity);
            final TextView price = view.findViewById(R.id.text_quantity);
            final TextView brand = view.findViewById(R.id.text_brand_name);

            Picasso.with(context).load(item.getImageUrl()).into(imageView);
            nametextView.setText(item.getName());
            gifted_to.setText("Emil Nielsen");
            quantity.setText("Qty: 1");
            price.setText((int) item.getPrice());
            brand.setText(item.getBrand());

            return view;
        }
    }
}
