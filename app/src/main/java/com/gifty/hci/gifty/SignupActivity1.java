package com.gifty.hci.gifty;

import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity1 extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private EditText ConfirmPasswword;
    private EditText BirthDate;

    private Button Next;
    private ImageButton Back;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount1);

        FirstName = (EditText) findViewById(R.id.etCreateAccount1_1);
        LastName = (EditText) findViewById(R.id.etCreateAccount1_2);
        Email = (EditText) findViewById(R.id.etCreateAccount1_3);
        Password = (EditText) findViewById(R.id.etCreateAccount1_4);
        ConfirmPasswword = (EditText) findViewById(R.id.etCreateAccount1_5);
        BirthDate = (EditText) findViewById(R.id.etCreateAccount1_6);

        Next = (Button) findViewById(R.id.btnNextsignup);
        Back = (ImageButton) findViewById(R.id.btnCreateaccount1_1);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mProgress = new ProgressDialog(this);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Backintent = new Intent(SignupActivity1.this, SigninActivity.class);
                startActivity(Backintent);
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startRegister();
            }
        });

    }

    private void startRegister() {

        final String firstname = FirstName.getText().toString().trim();
        final String lastname = LastName.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String birthdate = BirthDate.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String confirmpassword = ConfirmPasswword.getText().toString().trim();

        if (!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmpassword)) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user = mDatabase.child(user_id);
                        current_user.child("email").setValue(email);
                        current_user.child("first_name").setValue(firstname);
                        current_user.child("followers").setValue(null);
                        current_user.child("following").setValue(null);
                        current_user.child("last_name").setValue(lastname);
                        current_user.child("shoppingCart").setValue(null);
                        current_user.child("wishlists").setValue(null);
                        current_user.child("birthdate").setValue(birthdate);

                        Intent intent = new Intent(SignupActivity1.this, SignupActivity2.class);
                        intent.putExtra("name", firstname);
                        startActivity(intent);

                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            Toast.makeText(SignupActivity1.this, "Password should be longer than 6 characters", Toast.LENGTH_LONG).show();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            Toast.makeText(SignupActivity1.this, "Invalid Email", Toast.LENGTH_LONG).show();
                        } catch (FirebaseAuthUserCollisionException e) {
                            Toast.makeText(SignupActivity1.this, "Email is already used", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(SignupActivity1.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        } else {
            Toast.makeText(SignupActivity1.this, "Empty Fields", Toast.LENGTH_LONG).show();
        }
    }
}
