package com.example.bilalsalman.waterics;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminComplaintCenter extends Fragment {

    RecyclerView recyclerView;
    admin_complaintcardAdapter adapter;

    private FirebaseAuth mAuth;
    private DatabaseReference userdata;
    private List<admin_complaintcard> clist;
    private FragmentActivity mFrgAct;
    private Intent mIntent;

    Spinner spin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_admin_complaint_center, null);


        String [] country_names = {"All Complaints","Acknowledged","UnAcknowledged"};

        spin = (Spinner) root.findViewById(R.id.spinner12);

        spin.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,country_names));


        userdata = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        clist  = new ArrayList<>();


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                fillTheList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));


        return root;
    }


    public void onViewCreated(View view, Bundle savedInstanceState){

        final List<admin_complaintcard> cl = new ArrayList<>();


        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clist = new ArrayList<>();
                DataSnapshot complaintSnapshot = dataSnapshot.child("Complaints");
                Iterable<DataSnapshot> complaintChildren = complaintSnapshot.getChildren();

                String value = spin.getSelectedItem().toString();

                for (DataSnapshot complaint : complaintChildren) {

                    admin_complaintcard c = complaint.getValue(admin_complaintcard.class);

                    if (value.equals("All Complaints")) {
                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    } else if (value.equals("Acknowledged") && c.isAck()) {

                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    } else if (value.equals("UnAcknowledged") && !c.isAck()) {

                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    }


                }
                display();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        });


    }



    private void fillTheList()
    {

        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clist = new ArrayList<>();
                DataSnapshot complaintSnapshot = dataSnapshot.child("Complaints");
                Iterable<DataSnapshot> complaintChildren = complaintSnapshot.getChildren();

                String value = spin.getSelectedItem().toString();

                for (DataSnapshot complaint : complaintChildren) {

                    admin_complaintcard c = complaint.getValue(admin_complaintcard.class);

                    if (value.equals("All Complaints")) {
                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    } else if (value.equals("Acknowledged") && c.isAck()) {

                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    } else if (value.equals("UnAcknowledged") && !c.isAck()) {

                        clist.add(c);
                        //Toast.makeText(this.getContext(), "Complaint Found", Toast.LENGTH_SHORT).show();
                    }
                }
                display();
            }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    // ...
                }
        });

    }

    private void display()
    {
//        Toast.makeText(this.getContext(), "fired", Toast.LENGTH_SHORT).show();

        admin_complaintcardAdapter adapter = new admin_complaintcardAdapter(this.getActivity().getApplicationContext(), clist);
        adapter.notifyDataSetChanged();
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
