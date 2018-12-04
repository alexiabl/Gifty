package com.gifty.hci.gifty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ForgotPasswordActivity2 extends AppCompatActivity {

    private ImageButton Back;
    private EditText Code;
    private Button Next;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword2);

        Back = (ImageButton) findViewById(R.id.btnForgotpassword2);
        Code = (EditText) findViewById(R.id.etforgotpassword2);
        Next = (Button) findViewById(R.id.btnNext);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackIntent = new Intent(ForgotPasswordActivity2.this, ForgotPasswordActivity1.class);
                startActivity(BackIntent);
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = Code.getText().toString().trim();

            }
        });

    }
}
