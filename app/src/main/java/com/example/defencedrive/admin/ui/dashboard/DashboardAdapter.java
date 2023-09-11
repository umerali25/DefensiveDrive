package com.example.defencedrive.admin.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyHolder> {
    ArrayList<OrderHolder> dataHolders;
    Context context;
    OnCLickListener onCLickListener;

    public DashboardAdapter(ArrayList<OrderHolder> dataHolders, Context context, OnCLickListener onCLickListener) {
        this.dataHolders = dataHolders;
        this.context = context;
        this.onCLickListener = onCLickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.dashboard_row,parent,false);

        return new DashboardAdapter.MyHolder(view,onCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(dataHolders.get(position).getName());
        holder.date.setText(dataHolders.get(position).getDate());
        holder.phone.setText(dataHolders.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return dataHolders.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView name,date,phone;
        ConstraintLayout innerConst;
        OnCLickListener onCLickListener;
        public MyHolder(@NonNull View itemView, OnCLickListener onCLickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            phone = itemView.findViewById(R.id.phone);
            innerConst = itemView.findViewById(R.id.innercont);
            this.innerConst.setOnClickListener(this);
//            this.innerConst.setOnClickListener(this);
            this.onCLickListener =onCLickListener;
        }

        @Override
        public void onClick(View view) {
            onCLickListener.onCLick(view, getAdapterPosition());
        }
    }
}
