package com.example.bilalsalman.waterics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.msg.setText(complaint.msg);
        holder.eta.setText(complaint.eta);

        if(complaint.isAck()) {
            holder.ackstatus.setText("ACKNOWLEDGED");
            holder.eta.setEnabled(false);
        }
        else
            holder.ackstatus.setText("NOT ACKNOWLEDGED");

        /*holder.eta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(complaint.isAck())){
                    holder.eta.setText("");
                    holder.eta.getText().clear();
                }
            }
        });*/

        holder.eta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    holder.eta.setText("");
                }
            }
        });


        holder.acknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(complaint.isAck()) {
                    holder.eta.setEnabled(false);
                    holder.acknowledge.toggle();
                    return;
                }

                String etastr = holder.eta.getText().toString().trim();

                if(etastr.equals("") || etastr.equals("Eta not defined")) {
                    holder.eta.setText("");
                    holder.eta.setError("Enter estimated days to completion");
                    holder.eta.requestFocus();
                    holder.acknowledge.toggle();

                    return;
                }

                /*if want to hardcode
                if(complaint.type.equals("Pipe Burst")) {

                }
                if(complaint.type.equals("Leakage")) {

                }
                if(complaint.type.equals("Acidity in Water")) {

                }
                if(complaint.type.equals("Water Shortage")) {

                }*/
                etastr=etastr.concat(" days");
                complaint.acknowledge();
                holder.acknowledge.setChecked(complaint.isAck());
                if(complaint.isAck()){
                    holder.ackstatus.setText("ACKNOWLEDGED");

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Complaints").child(complaint.complaintID);
                    ref.child("ack").setValue(true);
                    ref.child("eta").setValue(etastr);

                    holder.eta.setEnabled(false);

                }
                else{
                    holder.ackstatus.setText("NOT ACKNOWLEDGED");
                    //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Complaints").child(complaint.getId());
                    //ref.child("ack").setValue(true);
                }
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

        public TextView complaintID,cdate,add,ackstatus,ctype,msg;
        public EditText eta;
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
            msg = itemView.findViewById(R.id.msg);
            eta = itemView.findViewById(R.id.eta);

            rLayout = itemView.findViewById(R.id.rLayout);
            llExpandArea = itemView.findViewById(R.id.llExpandArea);

            down = itemView.findViewById(R.id.expand);
        }
    }

}