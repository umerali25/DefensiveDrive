package com.example.defencedrive.tabfrags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.R;
import com.example.defencedrive.admin.ui.home.CenterDataHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyHolder>{

    ArrayList<CenterDataHolder> dataHolders;
    Context context;
    OnCLickListener onCLickListener;

    public TrainingAdapter(ArrayList<CenterDataHolder> holder, Context context, OnCLickListener onCLickListener) {
        this.dataHolders = holder;
        this.context = context;
        this.onCLickListener = onCLickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_row,parent,false);
        return new TrainingAdapter.MyHolder(view,onCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        FirebaseDatabase.getInstance().getReference().child("Centers").child(dataHolders.get(position).getCname()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imgurl = snapshot.child("url").getValue(String.class);
                if (imgurl == null)
                {

                }else {
                    Glide.with(context).load(imgurl).into(holder.imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.name.setText(dataHolders.get(position).getCname());
        holder.address.setText(dataHolders.get(position).getCaddress());
    }

    @Override
    public int getItemCount() {
        return dataHolders.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        ImageView imageView;
        TextView name,address;
        ConstraintLayout innerConst;
        OnCLickListener onCLickListener;
        public MyHolder(@NonNull View itemView, OnCLickListener onCLickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.center_img);
            name = itemView.findViewById(R.id.center_name);
            address = itemView.findViewById(R.id.center_address);
            innerConst = itemView.findViewById(R.id.innercont);
            this.innerConst.setOnClickListener(this);
            this.onCLickListener =onCLickListener;
        }

        @Override
        public void onClick(View view) {
            onCLickListener.onCLick(view, getAdapterPosition());
        }
    }
}
