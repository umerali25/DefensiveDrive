package com.example.defencedrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.defencedrive.AccountCreation.RegisterActivity;
import com.example.defencedrive.security.PandPActivity;
import com.example.defencedrive.security.TandCActivity;
import com.example.defencedrive.tabfrags.adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.security_menu,menu);
//        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                FirebaseAuth auth;
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
                return true;
            case R.id.tandc:
                startActivity(new Intent(MainActivity.this, TandCActivity.class));
                return true;
            case R.id.pandp:
                startActivity(new Intent(MainActivity.this, PandPActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
}
}