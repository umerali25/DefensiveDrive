package com.example.defencedrive.security;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.defencedrive.R;

public class PandPActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pand_pactivity);

        webView = findViewById(R.id.p_p);
        webView.loadUrl("https://www.termsfeed.com/live/b9574e76-c32c-4d93-8041-ba0f920d12f6");
    }
}