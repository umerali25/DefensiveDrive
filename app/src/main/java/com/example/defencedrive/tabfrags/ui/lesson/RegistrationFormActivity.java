package com.example.defencedrive.tabfrags.ui.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.defencedrive.BookingDoneActivity;
import com.example.defencedrive.OrderHolder;
import com.example.defencedrive.R;
import com.example.defencedrive.databinding.ActivityRegistrationFormBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class RegistrationFormActivity extends AppCompatActivity {
ActivityRegistrationFormBinding binding;
    RadioButton btn_1,btn_2;
    private String[] gender = {"Select Gender","Male","Female"};
    private String[] slots = {"Select Slots","Morning","AfterNoon","Evening"};
    private String[] course = {"Select Durations","1 Week","10 Days","1 Month"};
    private String[] car = {"Select Car","Auto","Manual"};
    FirebaseDatabase database;
    DatePickerDialog.OnDateSetListener listener;
    DatabaseReference reference;
    String Name,Email,Phone,Gender,Slots,Course,Car,Instructor,Date,image;
    String Center_uid,Center_Name,Address,Week,Half,Month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();



        btn_1=findViewById(R.id.radio_btn_1);
        btn_2= findViewById(R.id.radio_btn_2);
        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retriveImage();
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RegistrationFormActivity.this,listener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                dialog.show();
            }
        });
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Date = dayOfMonth + "/" + month + "/" + year;
                binding.date.setText(Date);
            }
        };
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Center_uid = intent.getStringExtra("center_id");
                Center_Name = intent.getStringExtra("center_name");
                Address = intent.getStringExtra("center_address");
                Week = intent.getStringExtra("center_week");
                Half = intent.getStringExtra("center_half");
                Month = intent.getStringExtra("center_month");

                Name = binding.name.getText().toString();
                Email= binding.Email.getText().toString();
                Phone = binding.phone.getText().toString();

                Date currentDate = new Date();
                // Get the timestamp in milliseconds
                long currentTimestamp = currentDate.getTime();
                String timeStamp = "" + currentTimestamp;
                String order_id = timeStamp;
                String user_id = FirebaseAuth.getInstance().getUid();

                if (Name == null || Name.equals(""))
                {
                    binding.name.setError("required");
                    binding.name.requestFocus();
                } else if (Email == null || Email.equals("")) {
                    binding.Email.setError("required");
                    binding.Email.requestFocus();
                } else if (Phone == null || Phone.equals("")) {
                    binding.phone.setError("required");
                    binding.phone.requestFocus();
                } else {
                    binding.progBar.setVisibility(View.VISIBLE);
                   database = FirebaseDatabase.getInstance();
                    OrderHolder holder = new OrderHolder(false,Center_uid,Center_Name,Address,order_id,user_id,Name,Email,Phone,Gender,Slots,Course,Car,Date,Instructor,image);
                    reference = database.getReference("Orders");
                   reference.child(timeStamp).setValue(holder).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           binding.progBar.setVisibility(View.INVISIBLE);
                           startActivity(new Intent(RegistrationFormActivity.this, BookingDoneActivity.class));
                           finish();
                       }
                   });

                }
            }
        });

        GenderFun();
        SlotFun();
        CourseFun();
        CarFun();
        radioFun();
    }

    private void retriveImage() {
        FirebaseDatabase.getInstance().getReference().child("Centers").child(""+Center_Name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                image = snapshot.child("url").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void radioFun() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    Instructor = radioButton.getText().toString();
                }
            }
        });
    }
    private void CarFun() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationFormActivity.this, android.R.layout.simple_spinner_dropdown_item,car);
        binding.car.setAdapter(adapter);
        binding.car.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Car = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void CourseFun() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationFormActivity.this, android.R.layout.simple_spinner_dropdown_item,course);
        binding.duration.setAdapter(adapter);
        binding.duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Course = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void SlotFun() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationFormActivity.this, android.R.layout.simple_spinner_dropdown_item,slots);
        binding.slots.setAdapter(adapter);
        binding.slots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Slots = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void GenderFun() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationFormActivity.this, android.R.layout.simple_spinner_dropdown_item,gender);
        binding.gender.setAdapter(adapter);
        binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}