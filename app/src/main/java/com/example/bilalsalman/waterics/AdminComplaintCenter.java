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

import java.util.ArrayList;
import java.util.List;

public class AdminComplaintCenter extends Fragment {

    RecyclerView recyclerView;
    admin_complaintcardAdapter adapter;
    List<admin_complaintcard> clist;

    private FragmentActivity mFrgAct;
    private Intent mIntent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_admin_complaint_center, null);


        /*recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        clist = new ArrayList<>();

        clist.add(new admin_complaintcard("8/8/18","A1","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A2","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A3","Johar","No water","Abdul Saeed","090078601","Never"));

        //creating recyclerview adapter
        admin_complaintcardAdapter adapter = new admin_complaintcardAdapter(this.getActivity().getApplicationContext(), clist);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);*/


        return root;
    }

    /*@Override
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
    }*/

    public void onViewCreated(View view, Bundle savedInstanceState){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        clist = new ArrayList<>();

        /*clist.add(new admin_complaintcard("8/8/18","A1","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A2","Johar","No water","Abdul Saeed","090078601","Never"));
        clist.add(new admin_complaintcard("8/8/18","A3","Johar","No water","Abdul Saeed","090078601","Never"));*/

        //creating recyclerview adapter
        admin_complaintcardAdapter adapter = new admin_complaintcardAdapter(this.getActivity().getApplicationContext(), clist);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
