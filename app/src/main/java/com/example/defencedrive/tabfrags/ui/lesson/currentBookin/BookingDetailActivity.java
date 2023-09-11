package com.example.defencedrive.tabfrags.ui.lesson.currentBookin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;
import com.example.defencedrive.databinding.ActivityBookingDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BookingDetailActivity extends AppCompatActivity {
ActivityBookingDetailBinding binding;
OrderHolder holder;
String Json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Json = getIntent().getStringExtra("holder");
        holder = new Gson().fromJson(Json,OrderHolder.class);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.cOrder.setText("Order Id: " + holder.getOrder_Id());
        binding.cName.setText("Center Name: " + holder.getCenter_Name());
        binding.cAddress.setText("Address: " + holder.getAddress());
        binding.cSlot.setText("Slot: " + holder.getSlots());
        binding.cCar.setText("Car: " + holder.getCar());
        binding.cDuration.setText("Duration: " + holder.getCourse_Duration());
        binding.cInstructor.setText("Instructor: " + holder.getInstructor());
    }
}