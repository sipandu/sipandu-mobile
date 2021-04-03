package com.sipanduteam.sipandu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    final Fragment berandaFragment = new BerandaFragment();
    final Fragment keluargaFragment = new KeluargaFragment();
    final Fragment posyanduFragment = new PosyanduFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    SharedPreferences userPreferences, loginPreferences;
    Fragment selectedFragment = berandaFragment;

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


        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = userPreferences.getString("email", "empty");


        //get local time for appbar title
        //TODO set username greeting from sharedpref
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String greeting;

        if (hour>= 12 && hour < 17) {
            greeting = "Selamat siang, ";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Selamat sore, ";
        } else if (hour >= 21 && hour < 24) {
            greeting = "Selamat malam, ";
        } else {
            greeting = "Selamat pagi, ";
        }

        greeting = greeting + email +"!";

//        berandaFragment = new BerandaFragment();
//        keluargaFragment = new KeluargaFragment();
//        posyanduFragment = new PosyanduFragment();
//        profileFragment = new ProfileFragment();

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle(greeting);

        bottomNavigationView = findViewById(R.id.home_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.home_fragment_container, profileFragment, "4").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.home_fragment_container, posyanduFragment, "3").hide(posyanduFragment).commit();
        fm.beginTransaction().add(R.id.home_fragment_container, keluargaFragment, "2").hide(keluargaFragment).commit();
        fm.beginTransaction().add(R.id.home_fragment_container, berandaFragment, "1").commit();

        // handler for snackbar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Login berhasil! Selamat datang " + email,Snackbar.LENGTH_SHORT);
                snackbar.setAnchorView(bottomNavigationView);
                snackbar.show();
            }
        }, 1000);

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
                    loginPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);

                    if (loginPreferences.getInt("login_status", 0) != 0) {
                        SharedPreferences.Editor editor = loginPreferences.edit();
                        editor.putInt("login_status", 0);
                        editor.putString("token", "empty");
                        editor.apply();
                    }
                    Toast.makeText(getApplicationContext(), "Logout berhasil", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(login);
                    finishAffinity();
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
                    if (item.getItemId() == R.id.nav_home) {
                        fm.beginTransaction().hide(selectedFragment).show(berandaFragment).commit();
                        selectedFragment = berandaFragment;
                    } else if (item.getItemId() == R.id.nav_keluargaku) {
                        fm.beginTransaction().hide(selectedFragment).show(keluargaFragment).commit();
                        selectedFragment = keluargaFragment;
                    } else if (item.getItemId() == R.id.nav_posyandu) {
                        fm.beginTransaction().hide(selectedFragment).show(posyanduFragment).commit();
                        selectedFragment = posyanduFragment;
                    } else if (item.getItemId() == R.id.nav_myprofile) {
                        fm.beginTransaction().hide(selectedFragment).show(profileFragment).commit();
                        selectedFragment = profileFragment;
                    }
//                    ft.beginTransaction();
//                    ft.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
//                    ft.replace(R.id.home_fragment_container, selectedFragment);
//                    ft.addToBackStack(null);
//                    ft.commit();
                    return true;
                }
            };
}