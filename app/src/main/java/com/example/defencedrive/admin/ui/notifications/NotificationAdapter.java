package com.example.defencedrive.admin.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;
import com.example.defencedrive.admin.ui.home.CenterDataHolder;
import com.example.defencedrive.tabfrags.TrainingAdapter;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
    ArrayList<OrderHolder> dataHolders;
    Context context;
    OnCLickListener onCLickListener;

    public NotificationAdapter(ArrayList<OrderHolder> dataHolders, Context context, OnCLickListener onCLickListener) {
        this.dataHolders = dataHolders;
        this.context = context;
        this.onCLickListener = onCLickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificatione_row,parent,false);
        return new NotificationAdapter.MyHolder(view,onCLickListener);
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
        AppCompatButton yes,no;
        ConstraintLayout innerConst;
        OnCLickListener onCLickListener;
        public MyHolder(@NonNull View itemView, OnCLickListener onCLickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            phone = itemView.findViewById(R.id.phone);
            yes = itemView.findViewById(R.id.confirm);
            no = itemView.findViewById(R.id.cancel);
            innerConst = itemView.findViewById(R.id.innercont);
            this.yes.setOnClickListener(this);
            this.no.setOnClickListener(this);
//            this.innerConst.setOnClickListener(this);
            this.onCLickListener =onCLickListener;
        }

        @Override
        public void onClick(View view) {
            onCLickListener.onCLick(view, getAdapterPosition());
        }
    }
}
