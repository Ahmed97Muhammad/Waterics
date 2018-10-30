package com.example.bilalsalman.waterics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class signin extends AppCompatActivity {

    TextView phone;

    String mVerificationId,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        phone = (TextView)findViewById(R.id.phone_signin);

        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = phone.getText().toString().trim();

                if (num.equals("")) {
                    phone.setError("Enter phone number in format: +923xxxxxxxxx!");
                    phone.requestFocus();
                    return;
                }

                Intent intent = new Intent(signin.this,SMSCode_in.class);
                intent.putExtra("num",num);
                startActivity(intent);

            }
        });
    }

    public void registernow(View view)
    {
        Intent intent = new Intent(signin.this, MainActivity.class);
        startActivity(intent);
    }



}
