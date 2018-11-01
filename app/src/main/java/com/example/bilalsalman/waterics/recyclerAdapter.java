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

    public recyclerAdapter(Context lists, List<cardViewClass> cards) {
        this.lists = lists;
        this.cards = cards;
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
        cardViewClass obj = cards.get(position);

        holder.viewId.setText(obj.getId());
        holder.viewack.setText(obj.getAck());
        holder.viewsupervisor.setText(obj.getSupervisor());
        holder.viewdt.setText(obj.getDate());
        holder.viewres.setText(obj.getRes());
        holder.viewtype.setText(obj.getType());

        Resources resources = lists.getResources();
        final int resourceId = resources.getIdentifier(obj.getImage(), "drawable", lists.getPackageName());

       // holder.imageview.setImageDrawable(resources.getDrawable(resourceId));

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class recycleView extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView viewId, viewsupervisor, viewack, viewdt, viewres, viewtype;

        public recycleView(View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageView);
            imageview.setImageResource(R.drawable.leak);
            viewId = itemView.findViewById(R.id.viewId);
            viewsupervisor = itemView.findViewById(R.id.viewsupervisor);
            viewack = itemView.findViewById(R.id.viewack);
            viewdt = itemView.findViewById(R.id.viewdt);
            viewres = itemView.findViewById(R.id.viewres);
            viewtype = itemView.findViewById(R.id.viewtype);
        }
    }

}
