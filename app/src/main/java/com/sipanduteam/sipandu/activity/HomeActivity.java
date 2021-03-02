package com.sipanduteam.sipandu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.fragment.home.*;


public class HomeActivity extends AppCompatActivity {

    private Toolbar homeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        getSupportActionBar().setTitle("Selamat pagi, X username here");
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getSupportActionBar().setTitle("Sipandu");
//            }
//        }, 1000);

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new BerandaFragment()).commit();

        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Login berhasil! Selamat datang X",Snackbar.LENGTH_SHORT);
        snackbar.setAnchorView(bottomNavigationView);
        snackbar.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    if (item.getItemId() == R.id.nav_home) {
                        selectedFragment = new BerandaFragment();
                    } else if (item.getItemId() == R.id.nav_keluargaku) {
                        selectedFragment = new KeluargaFragment();
                    } else if (item.getItemId() == R.id.nav_posyandu) {
                        selectedFragment = new PosyanduFragment();
                    } else if (item.getItemId() == R.id.nav_myprofile) {
                        selectedFragment = new ProfileFragment();
                    }
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
                    ft.replace(R.id.home_fragment_container, selectedFragment);
                    ft.commit();
                    return true;
                }
            };
}