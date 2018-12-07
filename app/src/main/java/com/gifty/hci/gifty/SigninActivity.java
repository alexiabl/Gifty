package com.gifty.hci.gifty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Activity for the user's profile
 *
 * @author Marwan Bushara
 */

public class SigninActivity extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Signin;
    private Button Signup;
    private Button ForgotPasswrod;
    private FirebaseAuth mAuth;
    private ImageView Logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Signin = (Button) findViewById(R.id.btnSignin);
        Signup = (Button) findViewById(R.id.btnSignup);
        ForgotPasswrod = (Button) findViewById(R.id.btnForgotPassword);
        mAuth = FirebaseAuth.getInstance();
        Logo = (ImageView) findViewById(R.id.ivLogo);

        String LogoUrilString = "https://firebasestorage.googleapis.com/v0/b/gifty-bd69a.appspot.com/o/gifty_logo.png?alt=media&token=b372eabd-9322-4aab-ad6d-49ee6918a559";
        Picasso.with(this).load(LogoUrilString).into(Logo);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignupIntent = new Intent(SigninActivity.this,SignupActivity1.class);
                startActivity(SignupIntent);
            }
        });

        ForgotPasswrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForgotPasswordIntent = new Intent(SigninActivity.this,ForgotPasswordActivity1.class);
                startActivity(ForgotPasswordIntent);
            }
        });

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();
            }
        });

    }

    private void checkLogin() {

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Intent intent = new Intent(SigninActivity.this,HomeActivity.class);
                        startActivity(intent);

                    }else{
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                        switch (errorCode) {
                            case "ERROR_INVALID_EMAIL":
                                Toast.makeText(SigninActivity.this,"Invalid Email", Toast.LENGTH_LONG).show();
                                break;
                            case "ERROR_WRONG_PASSWORD":
                                Toast.makeText(SigninActivity.this,"Wrong Password", Toast.LENGTH_LONG).show();
                                break;
                            case "ERROR_USER_NOT_FOUND":
                                Toast.makeText(SigninActivity.this,"No account registered with this Email", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(SigninActivity.this,"Error Login", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else {
            Toast.makeText(SigninActivity.this,"Empty Fields", Toast.LENGTH_LONG).show();
        }

    }

}

