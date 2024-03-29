package com.example.bilalsalman.waterics;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.recycleView> {

    private Context lists;
    private List<cardViewClass> cards;
    private List<admin_complaintcard> listofcards;


    /*public recyclerAdapter(Context lists, List<cardViewClass> cards) {
        this.lists = lists;
        this.cards = cards;
    }*/

    public recyclerAdapter(Context lists, List<admin_complaintcard> cards) {
        this.lists = lists;
        this.listofcards=cards;
    }

    @NonNull
    @Override
    public recycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater obj = LayoutInflater.from(lists);
        View obj1 = obj.inflate(R.layout.cardview, null);
        recycleView holder = new recycleView(obj1);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull recycleView holder, int position) {
        //cardViewClass obj = cards.get(position);

        /*holder.viewId.setText(obj.getId());
        holder.viewack.setText(obj.getAck());
        holder.viewsupervisor.setText(obj.getSupervisor());
        holder.viewdt.setText(obj.getDate());
        holder.viewres.setText(obj.getRes());
        holder.viewtype.setText(obj.getType());*/

        admin_complaintcard obj = listofcards.get(position);

        holder.viewId.setText(obj.getComplaintID());
        //holder.viewsupervisor.setText("Fix it yourself");
        holder.viewdt.setText(obj.getDate());
        holder.viewres.setText(obj.getEta());
        String complaintType = obj.getType();
        if(complaintType.equalsIgnoreCase("Pipe Burst")) {
            holder.imageview.setImageResource(R.drawable.pb);
        }
        else if(complaintType.equalsIgnoreCase("Water Shortage")) {
            holder.imageview.setImageResource(R.drawable.wshort);
        }
        else if(complaintType.equalsIgnoreCase("Acidity in Water")) {
            holder.imageview.setImageResource(R.drawable.acid);
        }
        else if(complaintType.equalsIgnoreCase("Leakage")) {
            holder.imageview.setImageResource(R.drawable.leak);
        }
        else {
            holder.imageview.setImageResource(R.drawable.waterdrop);
        }
        holder.viewtype.setText(obj.getType());

        if(obj.isAck())
        {
            holder.viewack.setText("Acknowledged");

            if(obj.type.equals("Pipe Burst")) {
                holder.viewsupervisor.setText("Tahir Mehmood");
            }
            if(obj.type.equals("Leakage")) {
                holder.viewsupervisor.setText("Kamran Nisar");
            }
            if(obj.type.equals("Acidity in Water")) {
                holder.viewsupervisor.setText("Nadeem Kafi");
            }
            if(obj.type.equals("Water Shortage")) {
                holder.viewsupervisor.setText("Abdul Saeed");
            }
        }
        else
        {
            holder.viewack.setText("Not Acknowledged");
            holder.viewsupervisor.setText("No supervisor assigned!");
        }




        //Resources resources = lists.getResources();
        //final int resourceId = resources.getIdentifier("waterdrop", "drawable", lists.getPackageName());
        //final int resourceId = resources.getIdentifier(obj.getImage(), "drawable", lists.getPackageName());

        // holder.imageview.setImageDrawable(resources.getDrawable(resourceId));

    }

    @Override
    public int getItemCount() {
        return listofcards.size();
    }

    class recycleView extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView viewId, viewsupervisor, viewack, viewdt, viewres, viewtype;

        public recycleView(View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageView);
            viewId = itemView.findViewById(R.id.viewId);
            viewsupervisor = itemView.findViewById(R.id.viewsupervisor);
            viewack = itemView.findViewById(R.id.viewack);
            viewdt = itemView.findViewById(R.id.viewdt);
            viewres = itemView.findViewById(R.id.viewres);
            viewtype = itemView.findViewById(R.id.viewtype);
        }
    }

}