package com.gifty.hci.gifty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity for the user's profile
 *
 * @author Marwan Bushara
 */

public class ForgotPasswordActivity4 extends AppCompatActivity {

    private Button Signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword4);

        Signin = (Button) findViewById(R.id.btnSignin2);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity4.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}
