package com.example.defencedrive.AccountCreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.defencedrive.AccountCreation.Holder.DataHolder;
import com.example.defencedrive.MainActivity;
import com.example.defencedrive.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.suBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.probar.setVisibility(View.VISIBLE);

                String Name = binding.suName.getText().toString();
                String Email = binding.suMail.getText().toString();
                String Pass = binding.suPass.getText().toString();

                if (Name == null || Name.isEmpty())
                {
                    binding.probar.setVisibility(View.INVISIBLE);
                    binding.suName.setError("required");
                    binding.suName.requestFocus();

                }
                else if (Email == null || Email.isEmpty())
                {
                    binding.probar.setVisibility(View.INVISIBLE);
                    binding.suMail.setError("required");
                    binding.suMail.requestFocus();
                }else if (Pass == null || Pass.isEmpty())
                {
                    binding.probar.setVisibility(View.INVISIBLE);
                    binding.suPass.setError("required");
                    binding.suPass.requestFocus();
                }
                else {
                    mauth = FirebaseAuth.getInstance();
                    mauth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                String UID = mauth.getCurrentUser().getUid();
                                DataHolder holder = new DataHolder(Name,Email,UID,false);
                                database = FirebaseDatabase.getInstance();
                                reference = database.getReference("Users");
                                reference.child(UID).setValue(holder).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        binding.probar.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();
                                        mauth.getCurrentUser();
                                    }
                                });
                            }
                        }
                    });
                }


            }
        });
        binding.haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

}