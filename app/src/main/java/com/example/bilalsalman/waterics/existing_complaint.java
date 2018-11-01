package com.example.bilalsalman.waterics;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;
import java.util.List;

public class existing_complaint extends Fragment{
    RecyclerView rview;
    recyclerAdapter adapter;

    List<cardViewClass> cards;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.existing_complaint, container, false);

        cards = new ArrayList<cardViewClass>();

//a dummy object just to check later objects from firebase
        cardViewClass obj = new cardViewClass("001","supervisor","not acknowledge","01/11/18","03/11/18","chutiyapa","waterdrop");
        cards.add(obj);

        cardViewClass obj1 = new cardViewClass("002","supervisor","not acknowledge","01/11/18","03/11/18","watershortage","waterdrop");
        cards.add(obj1);

        cardViewClass obj2 = new cardViewClass("003","Bilal","acknowledge","01/11/18","03/11/18","leak","waterdrop");
        cards.add(obj2);

        cardViewClass obj3 = new cardViewClass("004","Ahmed","not acknowledge","01/11/18","03/11/18","leak","waterdrop");
        cards.add(obj3);

        cardViewClass obj4 = new cardViewClass("005","Bilal Shafique","acknowledge","01/11/18","03/11/18","watershortage","waterdrop");
        cards.add(obj4);

        cardViewClass obj5 = new cardViewClass("006","supervisor","not acknowledge","01/11/18","03/11/18","chutiyappa","waterdrop");
        cards.add(obj5);

        cardViewClass obj6 = new cardViewClass("007","supervisor","not acknowledge","date","res","watershortage","waterdrop");
        cards.add(obj6);

        cardViewClass obj7 = new cardViewClass("008","supervisor","not acknowledge","date","res","watershortage","waterdrop");
        cards.add(obj7);

        rview = (RecyclerView) rootView.findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        adapter = new recyclerAdapter(this.getContext(), cards);
        rview.setAdapter(adapter);

        return rootView;

    }


}
