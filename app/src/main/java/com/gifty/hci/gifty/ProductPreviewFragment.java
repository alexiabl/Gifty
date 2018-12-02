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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ProductDao;
import com.gifty.hci.gifty.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductPreviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProductPreviewFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    private ProductDao productDao;

    public ProductPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_product_preview, null);
        GridView gridView = view.findViewById(R.id.grid_dashboard_items);
        final ArrayList<Product> products = (ArrayList<Product>) this.productDao.getAllProducts();
        final ProductGridAdapter productGridAdapter = new ProductGridAdapter(getActivity(), (Product[]) products.toArray());
        gridView.setAdapter(productGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product product = products.get(position);
                productGridAdapter.notifyDataSetChanged();
                //change view to product description
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Adapter class for the product items displayed in GridView in the frame layout
     */
    public static class ProductGridAdapter extends BaseAdapter {

        private final Product[] products;
        private final Context context;

        public ProductGridAdapter(Context context, Product[] products) {
            this.products = products;
            this.context = context;
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
