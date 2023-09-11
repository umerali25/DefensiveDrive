package com.example.defencedrive.tabfrags.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.defencedrive.tabfrags.GameFragment;
import com.example.defencedrive.tabfrags.ui.lesson.LessonFragment;
import com.example.defencedrive.tabfrags.TrainingFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return new LessonFragment();
        } else if (position==1)
        {
            return new TrainingFragment();
        }
        else{
            return new GameFragment();
        }
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
            return "Lesson";
        } else if (position==1)
        {
            return "Training";
        }
        else{
            return "Game";
        }
    }
}