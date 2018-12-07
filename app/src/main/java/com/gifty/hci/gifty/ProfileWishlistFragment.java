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
import android.widget.TextView;

import com.gifty.hci.gifty.model.Product;
import com.gifty.hci.gifty.model.Wishlist;
import com.squareup.picasso.Picasso;

/**
 * Fragment for list of a profile's wishlist
 *
 * @author alexiaborchgrevink
 */
public class ProfileWishlistFragment extends Fragment {


    public ProfileWishlistFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_wishlist, container, false);
    }

}
