package com.example.defencedrive.tabfrags.ui.lesson.currentBookin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.defencedrive.Interfaces.OnCLickListener;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.databinding.ActivityCurrentBookingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CurrentBookingActivity extends AppCompatActivity implements OnCLickListener {
    ActivityCurrentBookingBinding binding;
    CurrentAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    ArrayList<OrderHolder> holders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        reference = database.getReference().child("Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    OrderHolder data = dataSnapshot.getValue(OrderHolder.class);
                    if (data.getUser_id().equals( FirebaseAuth.getInstance().getUid()))
                    {
                        holders.add(data);
                    }
                    adapter = new CurrentAdapter(holders,CurrentBookingActivity.this,CurrentBookingActivity.this);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(CurrentBookingActivity.this));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter= new CurrentAdapter(holders,CurrentBookingActivity.this,this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recycler.setAdapter(adapter);

    }

    @Override
    public void onCLick(View view, int pos) {
        Intent intent = new Intent(CurrentBookingActivity.this,BookingDetailActivity.class);
        intent.putExtra("holder",new Gson().toJson(holders.get(pos)));
        startActivity(intent);
    }
}