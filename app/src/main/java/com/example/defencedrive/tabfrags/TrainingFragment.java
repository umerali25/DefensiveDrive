package com.example.defencedrive.tabfrags;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.admin.ui.home.CenterDataHolder;
import com.example.defencedrive.databinding.FragmentTrainingBinding;
import com.example.defencedrive.tabfrags.ui.lesson.RegistrationFormActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class TrainingFragment extends Fragment implements OnCLickListener {
    FragmentTrainingBinding binding;
    ArrayList<CenterDataHolder> data = new ArrayList<>();
    TrainingAdapter adapter;
    String image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrainingBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Centers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    CenterDataHolder holder = snapshot1.getValue(CenterDataHolder.class);
                    data.add(holder);
                }
                adapter = new TrainingAdapter(data,getActivity(),TrainingFragment.this);
                binding.crecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                binding.crecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onCLick(View view, int pos) {
        Intent intent = new Intent(getActivity(),RegistrationFormActivity.class);
        intent.putExtra("center_id",data.get(pos).getUID());
        intent.putExtra("center_name",data.get(pos).getCname());
        intent.putExtra("center_address",data.get(pos).getCaddress());
        intent.putExtra("center_week",data.get(pos).getWeek());
        intent.putExtra("center_half",data.get(pos).getHalfM());
        intent.putExtra("center_month",data.get(pos).getMonth());
        startActivity(intent);
    }
}