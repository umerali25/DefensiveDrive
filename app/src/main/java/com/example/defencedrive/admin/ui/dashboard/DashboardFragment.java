package com.example.defencedrive.admin.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.admin.ui.notifications.NotificationAdapter;
import com.example.defencedrive.admin.ui.notifications.NotificationsFragment;
import com.example.defencedrive.databinding.FragmentDashboardBinding;
import com.example.defencedrive.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements OnCLickListener {

   FragmentDashboardBinding binding;
    ArrayList<OrderHolder> holder =  new ArrayList<>();
    DashboardAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        orderConfirm();
        return root;
    }

    private void orderConfirm() {
        database = FirebaseDatabase.getInstance();
        reference= database.getReference().child("Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    OrderHolder data = dataSnapshot.getValue(OrderHolder.class);
                    if (data.getCenter_uid().equals(FirebaseAuth.getInstance().getUid()) && data.isOrder_Status())
                    {
                        holder.add(data);
                    }
                }
                adapter = new DashboardAdapter(holder,getActivity(), DashboardFragment.this);
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

    }
}