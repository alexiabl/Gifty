package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gifty.hci.gifty.dao.UserDao;
import com.gifty.hci.gifty.model.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WriteReviewActivity extends AppCompatActivity {

    private UserDao userDao = new UserDao();
    public WriteReviewActivity instance = this;
    Review review;
    EditText input_rating, input_review_title, input_review;
    Button insert;
    FirebaseDatabase database;
    DatabaseReference ref;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    intent = new Intent(instance, instance.getClass());
                    break;
                case R.id.nav_search_friends:
                    intent = new Intent(instance, SearchFriendsActivity.class);
                    break;
                case R.id.nav_notifications:
                    //intent = new Intent(instance,NotificationsActivity.class);
                    break;
                case R.id.nav_profile:
                    intent = new Intent(instance, ProfileActivity.class);
                    break;
            }
            getApplicationContext().startActivity(intent);
            return true;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        input_rating = EditText(findViewById(R.id.input_rating));
        input_review_title = EditText(findViewById(R.id.input_review_title));
        input_review = EditText(findViewById(R.id.input_review));
        insert = Button(findViewById(R.id.btn_post_review));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Product").child("0").child("reviews");
        review = new Review();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void getValues() {
        review.setDate("12/07/2018");
        review.setDescription(input_review.getText().toString());
        review.setRating(Integer.parseInt(input_rating.getText().toString()));
        review.setTitle(input_review_title.getText().toString());
    }

    public void btnInsert(View view) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                ref.child("11").setValue(review);
                Toast.makeText(WriteReviewActivity.this, "Review posted...", Toast.LENGTH_LONG);
                Intent intent = new Intent(instance, ReviewActivity.class);
                getApplicationContext().startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
