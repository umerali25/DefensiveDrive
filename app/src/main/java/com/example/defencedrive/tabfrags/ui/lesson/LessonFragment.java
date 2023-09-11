package com.example.defencedrive.tabfrags.ui.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.defencedrive.tabfrags.ui.lesson.currentBookin.CurrentBookingActivity;
import com.example.defencedrive.R;
import com.example.defencedrive.test_categories.SignTestActivity;



public class LessonFragment extends Fragment {
    private AppCompatButton instruct,sign_test,Hazard_video,Route,Current_Booking;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        init(view);
        instruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InstructionActivity.class);
                startActivity(intent);
            }
        });
        sign_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignTestActivity.class);
                startActivity(intent);
            }
        });
         Hazard_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HazardvideoActivity.class);
                startActivity(intent);
            }
        });
         Route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
         Current_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CurrentBookingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void init(View view) {
        instruct = view.findViewById(R.id.car);
        sign_test = view.findViewById(R.id.ltv);
        Hazard_video = view.findViewById(R.id.htv);
        Route = view.findViewById(R.id.route);
        Current_Booking = view.findViewById(R.id.c_booking);
    }
}