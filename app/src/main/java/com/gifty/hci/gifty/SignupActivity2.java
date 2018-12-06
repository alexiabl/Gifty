package com.gifty.hci.gifty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignupActivity2 extends AppCompatActivity {


    private TextView Name;
    private ImageButton AddPicture;
    private Button Skip;
    private Button Done;
    private ImageView Picture;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;


    private static final int PICK_IMAGE = 1;
    private Uri ImageUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        Name = (TextView) findViewById(R.id.tvCreateaccount2_1);
        Name.setText("Hello" + " " + name + ",");

        AddPicture = (ImageButton) findViewById(R.id.btnCreateaccount2_1);
        Skip = (Button) findViewById(R.id.btnCreateaccount2_2);
        Done = (Button) findViewById(R.id.btnCreateaccount2_3);
        Picture = (ImageView) findViewById(R.id.ivCreateaccount2_1);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mStorage = FirebaseStorage.getInstance().getReference().child("user_profilepic/");



        AddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SkipIntent = new Intent(SignupActivity2.this,HomeActivity.class);
                startActivity(SkipIntent);
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DoneIntent = new Intent(SignupActivity2.this,HomeActivity.class);
                startActivity(DoneIntent);
            }
        });





    }

    private void openGallery() {

        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode==PICK_IMAGE){
            ImageUri=data.getData();
            Picture.setImageURI(ImageUri);
            StorageReference filepath = mStorage.child(ImageUri.getLastPathSegment());
            filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getUploadSessionUri();
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user = mDatabase.child(user_id);
                    current_user.child("picture").setValue(uri.toString());
                }
            });




        }
    }
}
