package com.example.bilalsalman.waterics;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

    String userid;

    EditText message;

    CheckBox burst,leakage,acidity,shortage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.new_complaint, container, false);

        userdata = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        burst = rootView.findViewById(R.id.burst);
        leakage = rootView.findViewById(R.id.leakage);
        acidity = rootView.findViewById(R.id.acidity);
        shortage = rootView.findViewById(R.id.shortage);

        message = rootView.findViewById(R.id.message);

        Button submit_complain = (Button) rootView.findViewById(R.id.complain_submit);

        submit_complain.setOnClickListener(this);

        Button cencel_complain = (Button) rootView.findViewById(R.id.complain_cencel);

        cencel_complain.setOnClickListener(this);


        mAuth.getCurrentUser();
        userid=mAuth.getUid();


        return rootView;
    }

    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.complain_submit:
                getFromFirebase();
                break;

            case R.id.complain_cencel:
                cencelComplain();
                break;
        }
    }

    private void cencelComplain() {

        if (burst.isChecked())
        {
            burst.setChecked(false);
        }
        if (leakage.isChecked())
        {
            leakage.setChecked(false);
        }

        if (acidity.isChecked())
        {
            acidity.setChecked(false);
        }
        if (shortage.isChecked())
        {
            shortage.setChecked(false);
        }

        message.setText("");

    }

    String name = new String();
    String pnumber = new String();
    String address = new String();

    private void getFromFirebase()
    {
        if(!(burst.isChecked()||leakage.isChecked()||acidity.isChecked()||shortage.isChecked()))
        {
            Toast.makeText(this.getContext(), "Select complaint type!", Toast.LENGTH_SHORT).show();
            return;
        }

        ref.child("Users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("fname").getValue() != null)
                {
                    name = dataSnapshot.child("fname").getValue().toString();
                }
                if(dataSnapshot.child("num").getValue() != null)
                {
                    pnumber = dataSnapshot.child("num").getValue().toString();
                }
                if(dataSnapshot.child("address").getValue() != null)
                {
                    address = dataSnapshot.child("address").getValue().toString();
                }
                Save_to_FireBase();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void Save_to_FireBase()
    {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());



        Log.d("hello123",name);


        String msg= message.getText().toString();



        //Get data here from user.

        if(burst.isChecked())
        {
            String id = userdata.push().getKey();
            admin_complaintcard obj = new admin_complaintcard(currentDateandTime,id,address,"Pipe Burst",msg,"Eta not defined",userid);
            userdata.child("Complaints").child(id).setValue(obj);
        }
        if(leakage.isChecked())
        {
            String id = userdata.push().getKey();
            admin_complaintcard obj = new admin_complaintcard(currentDateandTime,id,address,"Leakage",msg,"Eta not defined",userid);
            userdata.child("Complaints").child(id).setValue(obj);
        }
        if(shortage.isChecked())
        {
            String id = userdata.push().getKey();
            admin_complaintcard obj = new admin_complaintcard(currentDateandTime,id,address,"Water Shortage",msg,"Eta not defined",userid);
            userdata.child("Complaints").child(id).setValue(obj);
        }
        if(acidity.isChecked())
        {
            String id = userdata.push().getKey();
            admin_complaintcard obj = new admin_complaintcard(currentDateandTime,id,address,"Acidity in Water",msg,"Eta not defined",userid);
            userdata.child("Complaints").child(id).setValue(obj);
        }

        Toast.makeText(this.getContext(), "Complaint Sent", Toast.LENGTH_SHORT).show();


        if (burst.isChecked())
        {
            burst.setChecked(false);
        }
        if (leakage.isChecked())
        {
            leakage.setChecked(false);
        }

        if (acidity.isChecked())
        {
            acidity.setChecked(false);
        }
        if (shortage.isChecked())
        {
            shortage.setChecked(false);
        }

        message.setText("");



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
