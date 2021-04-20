package com.sipanduteam.sipandu.activity.posyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.Kegiatan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KegiatanDetailActivity extends AppCompatActivity {

    String kegiatanKey = "KEGIATAN_ID";
    private TextView kegiatanTitle, kegiatanTempat, kegiatanStatusText, kegiatanWaktu;
    private MaterialCardView kegiatanStatus;
    WebView webView;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Toolbar homeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_detail);

        Gson gson = new Gson();
        Kegiatan kegiatan = gson.fromJson(getIntent().getStringExtra(kegiatanKey), Kegiatan.class);

        kegiatanTitle = findViewById(R.id.kegiatan_judul_text);
        kegiatanTempat = findViewById(R.id.kegiatan_lokasi_text);
        kegiatanStatusText = findViewById(R.id.kegiatan_status_text);
        kegiatanWaktu = findViewById(R.id.kegiatan_waktu_text);
        kegiatanStatus = findViewById(R.id.kegiatan_status);

        homeToolbar = findViewById(R.id.home_toolbar);
        homeToolbar.setTitle(kegiatan.getNamaKegiatan());
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        kegiatanTitle.setText(kegiatan.getNamaKegiatan());
        kegiatanTempat.setText(kegiatan.getTempat());
        try {
            if (format.parse(kegiatan.getEndAt()).after(new Date())) {
                kegiatanStatusText.setText("Sudah selesai");
                kegiatanStatus.setCardBackgroundColor(getResources().getColor(R.color.green));
            }
            else {
                kegiatanStatusText.setText("Belum terlaksana");
                kegiatanStatus.setCardBackgroundColor(getResources().getColor(R.color.secondaryColor));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        kegiatanWaktu.setText(kegiatan.getStartAt() + " hingga " + kegiatan.getEndAt());

        webView = findViewById(R.id.kegiatan_deskripsi_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadData(kegiatan.getDeskripsi(), "text/html; charset=UTF-8;", null);
    }
}