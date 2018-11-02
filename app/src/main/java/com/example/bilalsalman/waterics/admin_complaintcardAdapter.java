package com.example.bilalsalman.waterics;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class admin_complaintcardAdapter extends RecyclerView.Adapter<admin_complaintcardAdapter .admin_complaintcardViewHolder>{

    private Context mCtx;
    private List<admin_complaintcard> clist;
    private int expandedPosition = -1;

    public admin_complaintcardAdapter(Context mCtx, List<admin_complaintcard> clist) {
        this.mCtx = mCtx;
        this.clist = clist;
    }

    @NonNull
    @Override
    public admin_complaintcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.admin_cardview,null);
        return new admin_complaintcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final admin_complaintcardViewHolder holder, final int position) {
        final admin_complaintcard complaint = clist.get(position);

        holder.complaintID.setText(complaint.getComplaintID());
        holder.acknowledge.setChecked(complaint.isAck());
        holder.cdate.setText(complaint.date);
        holder.add.setText(complaint.add);
        holder.ctype.setText(complaint.type);
        holder.sname.setText(complaint.supername);
        holder.sphone.setText(complaint.superphone);
        holder.eta.setText(complaint.eta);

        if(complaint.isAck())
            holder.ackstatus.setText("ACKNOWLEDGED");
        else
            holder.ackstatus.setText("NOT ACKNOWLEDGED");


        holder.acknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint.acknowledge();
                holder.acknowledge.setChecked(complaint.isAck());
                if(complaint.isAck())
                    holder.ackstatus.setText("ACKNOWLEDGED");
                else
                    holder.ackstatus.setText("NOT ACKNOWLEDGED");
            }
        });

        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (position == expandedPosition) {
                    holder.llExpandArea.setVisibility(View.VISIBLE);
                } else {
                    holder.llExpandArea.setVisibility(View.GONE);
                }*/
                //holder.llExpandArea.setVisibility(View.VISIBLE);
                if (holder.llExpandArea.getVisibility() == View.GONE) {
                    // it's collapsed - expand it
                    holder.llExpandArea.setVisibility(View.VISIBLE);
                    holder.down.setImageResource(android.R.drawable.arrow_up_float);
                    //mDescriptionImg.setImageResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    // it's expanded - collapse it
                    holder.llExpandArea.setVisibility(View.GONE);
                    holder.down.setImageResource(android.R.drawable.arrow_down_float);
                    //mDescriptionImg.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }

                /*ObjectAnimator animation = ObjectAnimator.ofInt(holder.llExpandArea, "maxLines", holder.llExpandArea.getMaxLines());
                animation.setDuration(200).start();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    public class admin_complaintcardViewHolder extends RecyclerView.ViewHolder{

        public TextView complaintID,cdate,add,ackstatus,ctype,sname,sphone,eta;
        public Switch acknowledge;
        ImageButton down;


        public RelativeLayout llExpandArea;
        public RelativeLayout rLayout;

        public admin_complaintcardViewHolder(View itemView) {
            super(itemView);

            complaintID = itemView.findViewById(R.id.cid);
            acknowledge = itemView.findViewById(R.id.ack);
            cdate = itemView.findViewById(R.id.cdate);
            add = itemView.findViewById(R.id.add);
            ackstatus = itemView.findViewById(R.id.acknowledged);
            ctype = itemView.findViewById(R.id.ctype);
            sname = itemView.findViewById(R.id.supername);
            sphone = itemView.findViewById(R.id.superphone);
            eta = itemView.findViewById(R.id.eta);

            rLayout = itemView.findViewById(R.id.rLayout);
            llExpandArea = itemView.findViewById(R.id.llExpandArea);

            down = itemView.findViewById(R.id.expand);
        }
    }

}
