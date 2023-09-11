package com.example.defencedrive.test_categories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

import com.example.defencedrive.R;

public class HtvActivity extends AppCompatActivity {
    private WebView htv_WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htv);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        htv_WebView = findViewById(R.id.htv_webview);
        htv_WebView.loadUrl("https://www.trafficsigntest.com.pk/SignTest/New");
    }
}