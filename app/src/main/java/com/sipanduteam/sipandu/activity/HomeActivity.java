package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Selamat pagi, X username here");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar().setTitle("Sipandu");
            }
        }, 1000);

        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Login berhasil! Selamat datang X", Snackbar.LENGTH_SHORT).show();


    }
}