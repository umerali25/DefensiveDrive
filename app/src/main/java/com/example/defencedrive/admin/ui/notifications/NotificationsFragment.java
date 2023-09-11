package com.example.defencedrive.admin.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;
import com.example.defencedrive.databinding.FragmentNotificationsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements OnCLickListener {

    FragmentNotificationsBinding binding;
    ArrayList<OrderHolder> holder =  new ArrayList<>();
    NotificationAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        orderRequest();

        return root;
    }

    private void orderRequest() {
        database = FirebaseDatabase.getInstance();
        reference= database.getReference().child("Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    OrderHolder data = dataSnapshot.getValue(OrderHolder.class);
                    if (data.getCenter_uid().equals(FirebaseAuth.getInstance().getUid()) && !data.isOrder_Status())
                    {
                        holder.add(data);
                    }
                }
                adapter = new NotificationAdapter(holder,getActivity(), NotificationsFragment.this);
                binding.orderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.orderRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCLick(View view, int pos) {

        if (view.getId()==R.id.confirm)
        {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference().child("Orders").child(holder.get(pos).getOrder_Id()).child("order_Status");
            reference.setValue(Boolean.TRUE).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    orderRequest();
                }
            });


        }
        else if (view.getId()==R.id.cancel)
        {
            database = FirebaseDatabase.getInstance();
            reference= database.getReference().child("Orders").child(holder.get(pos).getOrder_Id());
            reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    orderRequest();
                }
            });
        }
    }
}