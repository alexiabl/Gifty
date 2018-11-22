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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileWishlistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileWishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileWishlistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileWishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileWishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileWishlistFragment newInstance(String param1, String param2) {
        ProfileWishlistFragment fragment = new ProfileWishlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_wishlist, container, false);
        //TODO: Set Profile wishlist adapter list items
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
    public static class WishlistListAdapter extends BaseAdapter {

        private final Wishlist[] wishlists;
        private final Context context;

        public WishlistListAdapter(Context context, Wishlist[] wishlists) {
            this.wishlists = wishlists;
            this.context = context;
        }

        @Override
        public int getCount() {
            return wishlists.length;
        }

        @Override
        public Object getItem(int i) {
            return wishlists[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // 1
            //find product in database by key
            final Wishlist item = wishlists[i];

            // 2
            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.fragment_profile_wishlist, null);
            }

            // 3
            final ImageView imageView = (ImageView) view.findViewById(R.id.image_wishlist_preview);
            final TextView nameTextView = (TextView) view.findViewById(R.id.text_wishlist_name);
            final TextView numItems = (TextView) view.findViewById(R.id.text_wishlist_items);
            final TextView deadline = (TextView) view.findViewById(R.id.text_wishlist_deadline);

            // 4
            //set wishlist values


            return view;
        }
    }
}
