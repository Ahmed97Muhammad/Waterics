package com.example.bilalsalman.waterics;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class new_complaint extends Fragment implements  View.OnClickListener{
    private FirebaseAuth mAuth;
    private DatabaseReference userdata;
    private DatabaseReference ref;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.new_complaint, container, false);

        userdata = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        Button submit_complain = (Button) rootView.findViewById(R.id.complain_submit);

        submit_complain.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.complain_submit:
                Save_to_FireBase();
                break;
        }
    }

    String name = new String();
    String pnumber = new String();
    String address = new String();


    private void Save_to_FireBase()
    {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        mAuth.getCurrentUser();
        String userid=mAuth.getUid();


        ref.child("Users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("fname").getValue() != null)
                    setname(dataSnapshot.child("fname").getValue().toString());
                if(dataSnapshot.child("num").getValue() != null)
                    setpnumber(dataSnapshot.child("num").getValue().toString());
                if(dataSnapshot.child("address").getValue() != null)
                    setaddress(dataSnapshot.child("address").getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String id = userdata.push().getKey();
        NewComplaintDataForFireBase obj = new NewComplaintDataForFireBase(currentDateandTime,"broken pipe",id,name,address,false,pnumber,"Testing Object");
        userdata.child("Complaints").child(id).setValue(obj);


        id = userdata.push().getKey();
        NewComplaintDataForFireBase obj1 = new NewComplaintDataForFireBase(currentDateandTime,"broken pipe",id,name,address,false,pnumber,"Testing Object");
        userdata.child("Complaints").child(id).setValue(obj1);


        Toast.makeText(this.getContext(), "Complaint Send To FireBase", Toast.LENGTH_SHORT).show();


    }

    private void setpnumber(String num)
    {
        pnumber = num;
    }

    private void setname(String fname)
    {
        name = fname;
    }

    private void setaddress(String addr)
    {
        address = addr;
    }


}
