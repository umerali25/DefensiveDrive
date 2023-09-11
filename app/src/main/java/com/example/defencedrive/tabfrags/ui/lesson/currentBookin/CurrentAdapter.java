package com.example.defencedrive.tabfrags.ui.lesson.currentBookin;

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
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;

import java.util.ArrayList;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.MyHolder> {

    ArrayList<OrderHolder> data;
    Context context;
    OnCLickListener onCLickListener;

    public CurrentAdapter(ArrayList<OrderHolder> data, Context context, OnCLickListener onCLickListener) {
        this.data = data;
        this.context = context;
        this.onCLickListener = onCLickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_row,parent,false);
        return new MyHolder(view,onCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(data.get(position).getCenter_Name());
        holder.address.setText(data.get(position).getAddress());
        if (data.get(position).isOrder_Status())
        {
            holder.innerConst.setBackgroundColor(R.drawable.green_corner);
        }
        else
        {
            holder.innerConst.setBackgroundColor(R.drawable.red_corner);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView name,address;
        ConstraintLayout innerConst;
        OnCLickListener onCLickListener;
        public MyHolder(@NonNull View itemView, OnCLickListener onCLickListener) {
            super(itemView);
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
