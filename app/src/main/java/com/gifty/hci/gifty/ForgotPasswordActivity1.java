package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Activity for the user's profile
 *
 * @author Marwan Bushara
 */

public class ForgotPasswordActivity1 extends AppCompatActivity {

    private EditText Email;
    private ImageButton Back;
    private Button Send;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotgotpassword1);

        Email = (EditText) findViewById(R.id.etForgotPasswordEmail);
        Back = (ImageButton) findViewById(R.id.btnForgotpassword1);
        Send = (Button) findViewById(R.id.btnSend);
        mAuth = FirebaseAuth.getInstance();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackIntent = new Intent(ForgotPasswordActivity1.this,SigninActivity.class);
                startActivity(BackIntent);
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPasswordActivity1.this,"Empty Fields", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent SendIntent = new Intent(ForgotPasswordActivity1.this,ForgotPasswordActivity4.class);
                                startActivity(SendIntent);
                            } else {
                                Toast.makeText(ForgotPasswordActivity1.this,"Invalid Email", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
