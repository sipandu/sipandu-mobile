package com.sipanduteam.sipandu.activity.posyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.model.posyandu.Kegiatan;
import com.sipanduteam.sipandu.model.posyandu.Pengumuman;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PengumumanDetailActivity extends AppCompatActivity {

    String pengumumanKey = "PENGUMUMAN_ID";
    private TextView pengumumanTitle, pengumumanDate, kegiatanWaktu;
    WebView webView;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Toolbar homeToolbar;
    private Chip statusChip;
    private ImageView pengumumanImage;
    private LinearLayout imageFailedLoad;
    private LinearLayout progressContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman_detail);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Gson gson = new Gson();
        Pengumuman pengumuman = gson.fromJson(getIntent().getStringExtra(pengumumanKey), Pengumuman.class);

        pengumumanImage = findViewById(R.id.pengumuman_detail_image);
        progressContainer = findViewById(R.id.pengumuman_image_progress);
        imageFailedLoad = findViewById(R.id.pengumuman_failed_image_load);

        //TODO hapus replaceAll nya, ga perlu klo production
        Picasso.get()
                .load(pengumuman.getFoto().replaceAll("http://192.168.1.104:1107", "https://sipandu-test-web.herokuapp.com"))
                .into(pengumumanImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressContainer.setVisibility(View.GONE);
                        pengumumanImage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressContainer.setVisibility(View.GONE);
                        imageFailedLoad.setVisibility(View.VISIBLE);
                    }
                });

        pengumumanTitle = findViewById(R.id.pengumuman_judul_text);
        pengumumanDate = findViewById(R.id.pengumuman_tanggal_text);
        pengumumanTitle.setText(pengumuman.getJudulPengumuman());
        pengumumanDate.setText(pengumuman.getTanggal());
        webView = findViewById(R.id.pengumuman_deskripsi_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadData(pengumuman.getPengumuman(), "text/html; charset=UTF-8;", null);

    }
}