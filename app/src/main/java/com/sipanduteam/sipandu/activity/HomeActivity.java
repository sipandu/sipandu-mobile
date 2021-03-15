package com.sipanduteam.sipandu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduMapActivity;
import com.sipanduteam.sipandu.fragment.home.*;

import java.util.Calendar;
import java.util.Date;


public class HomeActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    boolean doubleBack = false;
    BottomNavigationView bottomNavigationView;

    Fragment berandaFragment;
    Fragment keluargaFragment;
    Fragment posyanduFragment;
    Fragment profileFragment;
    FragmentTransaction ft;

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


        //get local time for appbar title
        //TODO set username greeting from sharedpref
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String greeting;

        if (hour>= 12 && hour < 17) {
            greeting = "Selamat siang, bagushikano!";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Selamat sore, bagushikano!";
        } else if (hour >= 21 && hour < 24) {
            greeting = "Selamat malam, bagushikano!";
        } else {
            greeting = "Selamat pagi, bagushikano!";
        }

        berandaFragment = new BerandaFragment();
        keluargaFragment = new KeluargaFragment();
        posyanduFragment = new PosyanduFragment();
        profileFragment = new ProfileFragment();

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle(greeting);

        bottomNavigationView = findViewById(R.id.home_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, berandaFragment).commit();

        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Login berhasil! Selamat datang X",Snackbar.LENGTH_SHORT);
        snackbar.setAnchorView(bottomNavigationView);
        snackbar.show();

        homeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home_app_bar_notification) {
                    Intent notification = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(notification);
                }

                else if (id == R.id.home_app_bar_about) {
                    Intent about = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(about);
                }

                else if (id == R.id.home_app_bar_logout) {
                    finish();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            this.finish();
        }
        else {
            this.doubleBack = true;
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Tekan sekali lagi untuk keluar",Snackbar.LENGTH_SHORT);
            snackbar.setAnchorView(bottomNavigationView);
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBack=false;
                }
            }, 1500);
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    if (item.getItemId() == R.id.nav_home) {
                        selectedFragment = berandaFragment;
                    } else if (item.getItemId() == R.id.nav_keluargaku) {
                        selectedFragment = keluargaFragment;
                    } else if (item.getItemId() == R.id.nav_posyandu) {
                        selectedFragment = posyanduFragment;
                    } else if (item.getItemId() == R.id.nav_myprofile) {
                        selectedFragment = profileFragment;
                    }
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
                    ft.replace(R.id.home_fragment_container, selectedFragment);
                    ft.commit();
                    ft.addToBackStack(null);
                    return true;
                }
            };
}