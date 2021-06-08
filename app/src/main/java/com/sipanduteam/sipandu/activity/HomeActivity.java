package com.sipanduteam.sipandu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.posyandu.PosyanduMapActivity;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.fragment.home.*;
import com.sipanduteam.sipandu.model.GenericApiResponse;
import com.sipanduteam.sipandu.model.GenericApiWithFlagResponse;
import com.sipanduteam.sipandu.viewmodel.InformasiBerandaViewModel;
import com.sipanduteam.sipandu.viewmodel.KeluargakuAnakViewModel;
import com.sipanduteam.sipandu.viewmodel.PengumumanViewModel;
import com.sipanduteam.sipandu.viewmodel.PosyanduViewModel;
import com.sipanduteam.sipandu.viewmodel.ProfileAnakViewModel;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class HomeActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    boolean doubleBack = false;
    BottomNavigationView bottomNavigationView;

    MenuItem notificationItem;

    InformasiBerandaViewModel informasiBerandaViewModel;
    ProfileAnakViewModel profileAnakViewModel;
    PosyanduViewModel posyanduViewModel;
    PengumumanViewModel pengumumanViewModel;
    KeluargakuAnakViewModel keluargakuAnakViewModel;

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
                R.id.navigation_beranda, R.id.navigation_keluarga, R.id.navigation_posyandu, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_home_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        informasiBerandaViewModel = ViewModelProviders.of(this).get(InformasiBerandaViewModel.class);
        profileAnakViewModel = ViewModelProviders.of(this).get(ProfileAnakViewModel.class);
        posyanduViewModel = ViewModelProviders.of(this).get(PosyanduViewModel.class);
        pengumumanViewModel = ViewModelProviders.of(this).get(PengumumanViewModel.class);
        keluargakuAnakViewModel = ViewModelProviders.of(this).get(KeluargakuAnakViewModel.class);
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

//        getNotificationCount(email);
    }

//    public void getNotificationCount(String email) {
//        InterfaceApi getData = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
//        Call<GenericApiWithFlagResponse> genericApiWithFlagResponseCall = getData.getUnreadNotifkasiCount(email);
//        genericApiWithFlagResponseCall.enqueue(new Callback<GenericApiWithFlagResponse>() {
//            @Override
//            public void onResponse(Call<GenericApiWithFlagResponse> call, Response<GenericApiWithFlagResponse> response) {
//                if (response.code() == 200 && response.body().getStatusCode() == 200) {
//                    if (response.body().getFlag() == 1) {
//                        notificationItem.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_notifications_24));
//                        notificationItem.setIconTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.red)));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GenericApiWithFlagResponse> call, Throwable t) {
//                //
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        notificationItem = menu.findItem(R.id.home_app_bar_notification);
        return super.onPrepareOptionsMenu(menu);
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

//    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    if (item.getItemId() == R.id.nav_home) {
//                        if (berandaFragment == null) {
//                            berandaFragment = new BerandaFragment();
//                        }
//                        fm.beginTransaction().setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast).hide(selectedFragment).show(berandaFragment).commit();
//                        selectedFragment = berandaFragment;
//                        return true;
//                    } else if (item.getItemId() == R.id.nav_keluargaku) {
//                        if (keluargaFragment == null) {
//                            keluargaFragment = new KeluargaFragment();
//                            fm.beginTransaction().add(R.id.home_fragment_container, keluargaFragment, "2").hide(keluargaFragment).commit();
//                        }
//                        fm.beginTransaction().setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast).hide(selectedFragment).show(keluargaFragment).commit();
//                        selectedFragment = keluargaFragment;
//                        return true;
//                    } else if (item.getItemId() == R.id.nav_posyandu) {
//                        if (posyanduFragment == null) {
//                            posyanduFragment = new PosyanduFragment();
//                            fm.beginTransaction().add(R.id.home_fragment_container, posyanduFragment, "3").hide(posyanduFragment).commit();
//                        }
//                        fm.beginTransaction().setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast).hide(selectedFragment).show(posyanduFragment).commit();
//                        selectedFragment = posyanduFragment;
//                        return true;
//                    } else if (item.getItemId() == R.id.nav_myprofile) {
//                        if (profileFragment == null) {
//                            profileFragment = new ProfileFragment();
//                            fm.beginTransaction().add(R.id.home_fragment_container, profileFragment, "4").hide(profileFragment).commit();
//                        }
//                        fm.beginTransaction().setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast).hide(selectedFragment).show(profileFragment).commit();
//                        selectedFragment = profileFragment;
//                        return true;
//                    }
////                    fm.beginTransaction().setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast).replace(R.id.home_fragment_container, selectedFragment).addToBackStack(null).commit();
////                    fm.beginTransaction();
////                    fm.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_fast);
////                    ft.replace(R.id.home_fragment_container, selectedFragment);
////                    ft.addToBackStack(null);
////                    ft.commit();
//                    return false;
//                }
//            };

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}