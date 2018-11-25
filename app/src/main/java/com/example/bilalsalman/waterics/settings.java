package com.example.bilalsalman.waterics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class settings extends AppCompatActivity {


    Button submit;
    Button cencel;

    int PLACE_PICKER_REQUEST = 1;
    TextView name,addr,pno,addrchange;

    private FirebaseAuth mAuth;
    private DatabaseReference userdata;
    private DatabaseReference ref;
    String userid,n,p,a,bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        name = (TextView)findViewById(R.id.textView10);
        addr = (TextView)findViewById(R.id.textView11);
        pno = (TextView)findViewById(R.id.textView12);
        addrchange =(TextView)findViewById((R.id.textView14));


        userdata = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();



        mAuth.getCurrentUser();
        userid=mAuth.getUid();

        getFromFireBase();


        submit = (Button)findViewById(R.id.addr_confirm);
        cencel = (Button)findViewById(R.id.addr_cencel);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (addrchange.getText().toString().trim().equals("Address"))
                {
                    Log.d("ahmed123","here");
                    addrchange.setError("Address is required!");
                    addrchange.requestFocus();
                    return;
                }

                getFromFireBase();

                Intent intent = new Intent(settings.this,SMSCode1.class);
                intent.putExtra("num",p);
                intent.putExtra("address",addrchange.getText().toString().trim());
                intent.putExtra("bill",bill);
                intent.putExtra("fname",n);

                startActivity(intent);
            }
        });



        cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!addr.equals("Address"))
                {
                    addr.setText("Address");
                }
            }
        });


    }




    private void getFromFireBase()
    {



            ref.child("Users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("fname").getValue() != null)
                    {
                        n = dataSnapshot.child("fname").getValue().toString();
                    }
                    if(dataSnapshot.child("num").getValue() != null)
                    {
                        p = dataSnapshot.child("num").getValue().toString();
                    }
                    if(dataSnapshot.child("address").getValue() != null)
                    {
                        a = dataSnapshot.child("address").getValue().toString();
                    }
                    if(dataSnapshot.child("bill").getValue() != null)
                    {
                        bill = dataSnapshot.child("bill").getValue().toString();
                    }
                    displayall();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }





    private void displayall()
    {

        name.setText("");
        pno.setText("");
        addr.setText("");

        name.setText("Name:  "+n);
        pno.setText("Phone Number: "+p);
        addr.setText("Address: "+a);
    }

    public void placepick1(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(settings.this), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(settings.this, data);
                Log.d("Ahmed",(String) place.getAddress());
                addrchange.setText(place.getAddress());
            }
        }
    }

}
