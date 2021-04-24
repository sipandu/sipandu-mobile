package com.sipanduteam.sipandu.activity.posyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class KegiatanDetailActivity extends AppCompatActivity {

    String kegiatanKey = "KEGIATAN_ID";
    private TextView kegiatanTitle, kegiatanTempat, kegiatanWaktu;
    WebView webView;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Toolbar homeToolbar;
    private Chip statusChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_detail);

        Gson gson = new Gson();
        Kegiatan kegiatan = gson.fromJson(getIntent().getStringExtra(kegiatanKey), Kegiatan.class);

        kegiatanTitle = findViewById(R.id.kegiatan_judul_text);
        kegiatanTempat = findViewById(R.id.kegiatan_lokasi_text);
        kegiatanWaktu = findViewById(R.id.kegiatan_waktu_text);
        statusChip = findViewById(R.id.kegiatan_status1);

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
        kegiatanWaktu.setText(kegiatan.getStartAt() + " hingga " + kegiatan.getEndAt());
        switch (kegiatan.getStatus()){
            case 0:
                statusChip.setText("Belum terlaksana");
                statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.secondaryLightColorSemiTransparent)));
                break;
            case 1:
                statusChip.setText("Sedang berjalan");
                statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primaryLightColorSemiTransparent)));
                break;
            case 2:
                statusChip.setText("Sudah selesai");
                statusChip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.greenSemiTransparent)));
                break;
        }

        webView = findViewById(R.id.kegiatan_deskripsi_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadData(kegiatan.getDeskripsi(), "text/html; charset=UTF-8;", null);
    }
}