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
import com.gifty.hci.gifty.model.User;

/**
 * Fragment for user item in a list or grid of Users
 * @author alexiaborchgrevink
 */
public class UserPreviewFragment extends Fragment {


    public UserPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_preview, container, false);

    }

}