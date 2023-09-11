package com.example.defencedrive.tabfrags.ui.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.defencedrive.R;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        getSupportActionBar().hide();
    }
}