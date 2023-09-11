package com.example.defencedrive.test_categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.defencedrive.R;
import com.example.defencedrive.databinding.ActivitySignTestBinding;

public class SignTestActivity extends AppCompatActivity {
    ActivitySignTestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignTestActivity.this,CarActivity.class));
            }
        });
        binding.ltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignTestActivity.this,LtvActivity.class));
            }
        });
        binding.htv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignTestActivity.this,HtvActivity.class));
            }
        });
    }
}