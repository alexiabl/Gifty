package com.gifty.hci.gifty;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gifty.hci.gifty.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CreateWishlistActivity extends Activity {

    private Button createButton;
    private ImageButton backbutton;
    private User currentUser;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = dbRef.child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wishlist);

        createButton = findViewById(R.id.button_create_wishlist);
        backbutton = findViewById(R.id.img_top_bar_back);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        Query query = this.userRef.child(userId);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: get edit text fields and create a new wishlist
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
