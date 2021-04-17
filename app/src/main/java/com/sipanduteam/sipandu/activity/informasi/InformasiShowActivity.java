package com.sipanduteam.sipandu.activity.informasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;

import static android.view.View.GONE;

public class InformasiShowActivity extends AppCompatActivity {

    WebView webView;
    private Toolbar homeToolbar;

    LinearLayout loadingContainer, failedContainer;
    Button refreshInformasi;

    String informasiKey = "INFORMASI_LINK", informasiTitleKey = "INFORMASI_TITLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_show);

        loadingContainer = findViewById(R.id.informasi_loading_container);
        failedContainer = findViewById(R.id.informasi_failed_container);

        Bundle extras = getIntent().getExtras();
        String informasiLink = "https://sipandu-test-web.herokuapp.com/blog/detail/" + extras.getString(informasiKey);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle(extras.getString(informasiTitleKey));
        homeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        refreshInformasi = findViewById(R.id.show_informasi_refresh);

        webView = findViewById(R.id.show_informasi_webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                setLoadingContainerVisible();
                return true;
            }

            //TODO add handler klo misal page gagal di load
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                setFailedContainerVisible();
//                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
//                        "Error while loading the map, please check your internet connection or try again later",
//                        Snackbar.LENGTH_SHORT).show();
//
//            }
            @Override
            public void onPageFinished(WebView view, String url) {
                setWebViewVisible();
                super.onPageFinished(view, url);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(informasiLink);

        refreshInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLoadingContainerVisible();
                webView.reload();
            }
        });

    }

    public void setFailedContainerVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(View.VISIBLE);
        webView.setVisibility(GONE);
    }

    public void setLoadingContainerVisible() {
        loadingContainer.setVisibility(View.VISIBLE);
        failedContainer.setVisibility(GONE);
        webView.setVisibility(GONE);
    }

    public void setWebViewVisible() {
        loadingContainer.setVisibility(GONE);
        failedContainer.setVisibility(GONE);
        webView.setVisibility(View.VISIBLE);
    }
}