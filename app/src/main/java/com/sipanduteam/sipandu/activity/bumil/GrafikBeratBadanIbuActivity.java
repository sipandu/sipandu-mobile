package com.sipanduteam.sipandu.activity.bumil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;

public class GrafikBeratBadanIbuActivity extends AppCompatActivity {

    WebView webView;
    private Toolbar homeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_berat_badan_ibu);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webView = findViewById(R.id.grafik_kesehatan);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                        "Gagal menampilkan grafik, silahkan coba lagi nanti",
                        Snackbar.LENGTH_SHORT).show();

            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://sipandu-test-web.herokuapp.com/mobile/data-kesehatan/graph-ibu/" + getIntent().getIntExtra("ID", -1));
    }
}