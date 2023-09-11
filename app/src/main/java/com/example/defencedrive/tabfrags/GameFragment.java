package com.example.defencedrive.tabfrags;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.defencedrive.R;

import java.util.Objects;


public class GameFragment extends Fragment {


    private Button button;
    Intent intent;
    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_game,container,false);
        button = view.findViewById(R.id.button);
        intent = requireContext().getPackageManager().getLaunchIntentForPackage("com.DefaultCompany.ddgame");

        button.setOnClickListener(v -> {

            try {

                if (intent != null){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            } catch (Exception e) {
                Toast.makeText(requireActivity(),""+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        return view;


    }
}