package com.example.bilalsalman.waterics;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    //FirebaseAuth mAuth;
    TextView add,billid,phone,name;
    Button signup;
    String  mVerificationId;
    Spinner spin;

    PhoneAuthProvider.ForceResendingToken mResendToken;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);


        signup = (Button)findViewById(R.id.signup);
        add = (TextView)findViewById(R.id.add);
        name = (TextView)findViewById(R.id.name);
        phone = (TextView)findViewById(R.id.phone);
        billid = (TextView)findViewById(R.id.billid);
        //Toast.makeText(getApplicationContext(), "Lets Begin!!!", Toast.LENGTH_SHORT).show();

        spin = (Spinner)findViewById(R.id.spinner);
        String [] country_names = {"Pakistan","India","Afghanistan","Bangladesh"};


        spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,country_names));



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Clicked Signup", Toast.LENGTH_SHORT).show();
                String bill = billid.getText().toString().trim();
                String num = phone.getText().toString().trim();
                String address = add.getText().toString().trim();
                String fname = name.getText().toString().trim();

                if (num.equals("")) {
                    phone.setError("Enter phone number in format: +923xxxxxxxxx!");
                    phone.requestFocus();
                    return;
                }

                if (bill.equals("")) {
                    billid.setError("Bill ID is required!");
                    billid.requestFocus();
                    return;
                }

                if (address.equals("")) {
                    add.setError("Address is required!");
                    add.requestFocus();
                    return;
                }

                String c_code = "";
                String cc_name = (String) spin.getSelectedItem();
                if(cc_name == "Pakistan")
                    c_code = "+92";
                if(cc_name == "India")
                    c_code = "+92";
                if(cc_name == "Afghanistan")
                    c_code = "+92";
                if(cc_name == "Bangladesh")
                    c_code = "+92";


                Intent intent = new Intent(MainActivity.this,SMSCode.class);
                intent.putExtra("num",num);
                intent.putExtra("bill",bill);
                intent.putExtra("address",address);
                intent.putExtra("fname",fname);
                intent.putExtra("c_code",c_code);
                startActivity(intent);
            }
        });



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
                Log.d("Ahmed",(String) place.getAddress());
                add.setText(place.getAddress());
            }
        }
    }



}