package com.example.bilalsalman.waterics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminComplaintCenter extends AppCompatActivity {

    RecyclerView recyclerView;
    admin_complaintcardAdapter adapter;
    List<admin_complaintcard> clist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaint_center);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clist = new ArrayList<>();

        clist.add(new admin_complaintcard("8/8/18","A1","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A2","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A3","Johar","No water","Abdul Saeed","090078601","Never"));

        //creating recyclerview adapter
        admin_complaintcardAdapter adapter = new admin_complaintcardAdapter(this, clist);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
