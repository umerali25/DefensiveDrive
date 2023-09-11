package com.example.defencedrive.AccountCreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.defencedrive.AccountCreation.Holder.DataHolder;
import com.example.defencedrive.ForgotActivity;
import com.example.defencedrive.MainActivity;
import com.example.defencedrive.R;
import com.example.defencedrive.admin.AdminActivity;
import com.example.defencedrive.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    private EditText email,pass;
    private Button signIn;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        binding.forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
                finish();
            }
        });
        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.siPro.setVisibility(View.VISIBLE);
                String Email = email.getText().toString();
                String Pass = pass.getText().toString();

                if (Email.equals("") || Email.isEmpty())
                {
                    email.setError("email required");
                    email.requestFocus();
                }else if (Pass.equals("") || Pass.isEmpty())
                {
                    pass.setError("password required");
                    pass.requestFocus();
                }else {
                    auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            binding.siPro.setVisibility(View.INVISIBLE);
                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    DataHolder holder = snapshot.getValue(DataHolder.class);
                                    if (holder.isAdmin()){
                                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                                        finish();
                                    }else {
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            auth.getCurrentUser();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, " " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


    }

    private void initView() {
        email = findViewById(R.id.si_mail);
        pass = findViewById(R.id.su_pass);
        signIn = findViewById(R.id.btnLogin);

    }
}