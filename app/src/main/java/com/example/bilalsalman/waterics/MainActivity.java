package com.example.bilalsalman.waterics;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    FirebaseAuth mAuth;
    TextView add,billid,pass,phone,name;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        signup = (Button)findViewById(R.id.signup);
        add = (TextView)findViewById(R.id.add);
        name = (TextView)findViewById(R.id.name);
        phone = (TextView)findViewById(R.id.phone);
        pass = (TextView)findViewById(R.id.pass);
        billid = (TextView)findViewById(R.id.billid);

    }

    public void placepick(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(MainActivity.this, data);
                add.setText(place.getAddress());
            }
        }
    }

    private void registration() {
        String email = billid.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String address = add.getText().toString().trim();

        if (email.isEmpty()) {
            billid.setError("Email is required!");
            billid.requestFocus();
        }

        if (password.isEmpty()) {
            pass.setError("Password is required!");
            pass.requestFocus();
        }

        if (password.length() < 5) {
            pass.setError("Minimum length of password should be 5");
            pass.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            add.setError("Address is required!");
            add.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.signup:
                registration();
                break;
        }
    }

}
