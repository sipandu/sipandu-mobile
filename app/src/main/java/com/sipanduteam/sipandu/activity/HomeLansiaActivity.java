package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import com.sipanduteam.sipandu.viewmodel.InformasiBerandaViewModel;
import com.sipanduteam.sipandu.viewmodel.KeluargakuIbuViewModel;
import com.sipanduteam.sipandu.viewmodel.KeluargakuLansiaViewModel;
import com.sipanduteam.sipandu.viewmodel.PengumumanViewModel;
import com.sipanduteam.sipandu.viewmodel.PosyanduViewModel;
import com.sipanduteam.sipandu.viewmodel.ProfileIbuViewModel;
import com.sipanduteam.sipandu.viewmodel.ProfileLansiaViewModel;

import java.util.Calendar;
import java.util.Date;

public class HomeLansiaActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    boolean doubleBack = false;
    BottomNavigationView bottomNavigationView;

    InformasiBerandaViewModel informasiBerandaViewModel;
    ProfileLansiaViewModel profileLansiaViewModel;
    PosyanduViewModel posyanduViewModel;
    PengumumanViewModel pengumumanViewModel;
    KeluargakuLansiaViewModel keluargakuLansiaViewModel;

    //    private Fragment berandaFragment = new BerandaFragment();
//    private Fragment keluargaFragment;
//    private Fragment posyanduFragment;
//    private Fragment profileFragment;
    final FragmentManager fm = getSupportFragmentManager();
    SharedPreferences userPreferences, loginPreferences;
//    Fragment selectedFragment = berandaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lansia);

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
        int role = userPreferences.getInt("role", 4);
        String namaUser = userPreferences.getString("nama_user", "empty");

        String[] arrayString = namaUser.split(" ");

        //get local time for appbar title
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

        greeting = greeting + arrayString[arrayString.length-1] +"!";

//        berandaFragment = new BerandaFragment();
//        keluargaFragment = new KeluargaFragment();
//        posyanduFragment = new PosyanduFragment();
//        profileFragment = new ProfileFragment();

        homeToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle(greeting);

        //TODO repair fragment creation biar nggak di create bareng bareng

        bottomNavigationView = findViewById(R.id.home_bottom_nav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
//        fm.beginTransaction().add(R.id.home_fragment_container, berandaFragment, "1").commit();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beranda, R.id.navigation_keluarga_lansia, R.id.navigation_posyandu, R.id.navigation_lansia_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        informasiBerandaViewModel = ViewModelProviders.of(this).get(InformasiBerandaViewModel.class);
        profileLansiaViewModel = ViewModelProviders.of(this).get(ProfileLansiaViewModel.class);
        posyanduViewModel = ViewModelProviders.of(this).get(PosyanduViewModel.class);
        pengumumanViewModel = ViewModelProviders.of(this).get(PengumumanViewModel.class);
        keluargakuLansiaViewModel = ViewModelProviders.of(this).get(KeluargakuLansiaViewModel.class);
        pengumumanViewModel.init(email, role);


        // handler for snackbar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar snackbar = Snackbar.make(
                        getWindow().getDecorView().findViewById(android.R.id.content),
                        "Login berhasil! Selamat datang " + arrayString[arrayString.length-1] + "!",Snackbar.LENGTH_SHORT);
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
}