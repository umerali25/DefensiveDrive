package com.example.defencedrive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.defencedrive.databinding.ActivityForgotBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    ActivityForgotBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.siMail.getText().toString();

                if (email == null || email.equals("")){
                    binding.siMail.setError("Enter valid email");
                    binding.siMail.requestFocus();
                }
                else {
                    binding.peoBar.setVisibility(View.VISIBLE);
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ForgotActivity.this, "You can change your password via link which is send to you email address", Toast.LENGTH_SHORT).show();
                            binding.siMail.setText("");
                            binding.peoBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}