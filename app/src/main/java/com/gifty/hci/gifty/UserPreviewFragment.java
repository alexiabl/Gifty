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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserPreviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UserPreviewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public UserPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_preview, container, false);

        //TODO: Set user results to UserGridAdapter from UserDAO method (to implement)

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
    public static class UserGridAdapter extends BaseAdapter {

        private final User[] users;
        private final Context context;

        public UserGridAdapter(Context context, User[] users) {
            this.users = users;
            this.context = context;
        }

        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int i) {
            return users[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final User item = users[i];

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.fragment_product_preview, null);
            }

            // 3
            final ImageView imageView = (ImageView) view.findViewById(R.id.image_search_friends_profile);
            final TextView nameTextView = (TextView) view.findViewById(R.id.text_user_name);
            final TextView numItems = (TextView) view.findViewById(R.id.text_num_wishlists);
            final TextView deadline = (TextView) view.findViewById(R.id.text_wishlist_deadline);


            //4
            //Set values

            return view;
        }
    }

    ;
}