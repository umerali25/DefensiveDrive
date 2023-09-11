package com.example.defencedrive.security;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.defencedrive.R;

public class TandCActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tand_cactivity);
        webView= findViewById(R.id.t_c);
        webView.loadUrl("https://www.termsfeed.com/live/d80018d0-9d4e-4085-9737-a5d11855a691");
    }
}