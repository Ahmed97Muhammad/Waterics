package com.example.bilalsalman.waterics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void registernow(View view)
    {
        Intent intent = new Intent(signin.this, MainActivity.class);
        startActivity(intent);
    }
}
