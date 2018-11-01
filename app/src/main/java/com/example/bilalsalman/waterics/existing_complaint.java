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
        cardViewClass obj = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj);

        cardViewClass obj1 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj1);

        cardViewClass obj2 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj2);

        cardViewClass obj3 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj3);

        cardViewClass obj4 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj4);

        cardViewClass obj5 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj5);

        cardViewClass obj6 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj6);

        cardViewClass obj7 = new cardViewClass("id","supervisor","not acknowledge","date","res","watershortage","bg1.jpeg");
        cards.add(obj7);

        rview = (RecyclerView) rootView.findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        adapter = new recyclerAdapter(this.getContext(), cards);
        rview.setAdapter(adapter);

        return rootView;

    }


}
