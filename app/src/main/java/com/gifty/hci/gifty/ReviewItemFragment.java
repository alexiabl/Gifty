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
import android.widget.RatingBar;
import android.widget.TextView;

import com.gifty.hci.gifty.dao.ReviewDao;
import com.gifty.hci.gifty.model.Review;

import java.util.ArrayList;

public class ReviewItemFragment extends Fragment {

    private ProductPreviewFragment.OnFragmentInteractionListener mListener;

    private ReviewDao reviewDao;

    public ReviewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_reviews, null);
        ListView listView = view.findViewById(R.id.reviews);
        final ArrayList<Review> reviews = (ArrayList<Review>) this.reviewDao.getAllReviews();
        final ReviewItemFragment.ReviewAdapter reviewAdapter = new ReviewItemFragment.ReviewAdapter(getActivity(), (Review[]) reviews.toArray());
        listView.setAdapter(reviewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                reviewAdapter.notifyDataSetChanged();
                //change view to product description
            }
        });
        return view;

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class ReviewAdapter extends BaseAdapter {
        private final Review[] reviews;
        private Context context;

        public ReviewAdapter(Context context, Review[] reviews) {
            this.reviews = reviews;
            this.context = context;
        }

        @Override
        public int getCount() {
            return reviews.length;
        }

        @Override
        public Object getItem(int i) {
            return reviews[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            final Review item = reviews[i];

            if (view == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                view = layoutInflater.inflate(R.layout.fragment_reviews, null);
            }

            final TextView review_title = view.findViewById(R.id.review_title);
            final TextView review_detail = view.findViewById(R.id.review_detail);
            final TextView review_date = view.findViewById(R.id.review_date);
            final RatingBar ratingBar = view.findViewsWithText(R.id.ratingBar);

            review_title.setText(item.getTitle());
            review_detail.setText(item.getDescription());
            review_date.setText(item.getDate());
            ratingBar.setRating(item.getRating());

            return view;
        }
    }

}
