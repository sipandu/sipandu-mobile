package com.sipanduteam.sipandu.activity.anak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;

public class GrafikAnakActivity extends AppCompatActivity {

    WebView webView;
    private Toolbar homeToolbar;
    private TextView grafikTitle;
    private String urlGrafik;
    private String baseUrlGrafik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_anak);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        grafikTitle = findViewById(R.id.grafik_title_anak_text);

        int flag = getIntent().getIntExtra("FLAG", -1);
        if (flag == 0) {
            grafikTitle.setText("Grafik berat badan menurut tinggi badan anak");
            baseUrlGrafik = "https://sipandu-test-web.herokuapp.com/mobile/data-kesehatan/graph-anak-1/";
        } else if (flag == 1) {
            grafikTitle.setText("Grafik berat badan menurut umur anak");
            baseUrlGrafik = "https://sipandu-test-web.herokuapp.com/mobile/data-kesehatan/graph-anak-2/";
        } else if (flag == 2) {
            grafikTitle.setText("Grafik tinggi badan menurut umur anak");
            baseUrlGrafik = "https://sipandu-test-web.herokuapp.com/mobile/data-kesehatan/graph-anak-3/";
        } else if (flag == 3) {
            grafikTitle.setText("Grafik lingkar kepala menurut umur");
            baseUrlGrafik = "https://sipandu-test-web.herokuapp.com/mobile/data-kesehatan/graph-anak-4/";
        }

        urlGrafik = baseUrlGrafik + getIntent().getIntExtra("ID", -1);

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
        webView.loadUrl(urlGrafik);
    }
}