package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.register.RegisterActivity;
import com.sipanduteam.sipandu.activity.register.RegisterDataAnakActivity;
import com.sipanduteam.sipandu.activity.register.RegisterDataIbuActivity;
import com.sipanduteam.sipandu.activity.register.RegisterDataLansiaActivity;
import com.sipanduteam.sipandu.api.InterfaceApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.user.UserLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //TODO untuk loginnya tolong di catet juga nanti role nya, pake sharedpref aja, biar nanti gampang untuk nentuin setiap viewnya yg mana,
    // catet juga informasi penting usernya kek nama, rolenya,dll di sharedpref nya nanti

    TextInputLayout usernameLayout, passwordLayout;
    TextInputEditText usernameForm, passwordForm;
    Button loginButton, registerButton, forgotPassButton;
    SharedPreferences loginPreferences, userPreferences;
    private String token;
    private Dialog dialog;
    private int loginStatus, role;
//    VideoView loginBg;
    boolean doubleBack = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE  | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

//        Window window = getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

//        Window w = getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //inflate everything
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.reg_button);
        forgotPassButton = findViewById(R.id.forgot_pass_button);
        usernameLayout = findViewById(R.id.username_form);
        passwordLayout = findViewById(R.id.password_form);
        usernameForm = findViewById(R.id.username_text_field);
        passwordForm = findViewById(R.id.password_text_field);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.progress_dialog_layout);
        ProgressBar prog = (ProgressBar) dialog.findViewById(R.id.spin_kit);

        loginPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        loginStatus = loginPreferences.getInt("login_status", 0);
        role = userPreferences.getInt("role", 4);


//        loginBg = (VideoView) findViewById(R.id.login_bg_video);

//        loginBg.setVideoPath("http://192.168.1.103:8000/video/1.mp4");
//        loginBg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//                float videoProportion =  (float) mp.getVideoHeight() / mp.getVideoWidth();
//
//                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//                Point size = new Point();
//                wm.getDefaultDisplay().getRealSize(size);
//                final int width = size.x, height = size.y;
//
//
////                int screenWidth = getResources().getDisplayMetrics().widthPixels;
////                int screenHeight = getResources().getDisplayMetrics().heightPixels;
//
//                int screenWidth = width;
//                int screenHeight = height;
//
//                float screenProportion = (float) screenHeight / (float) screenWidth;
//                android.view.ViewGroup.LayoutParams lp = loginBg.getLayoutParams();
//
//                if (videoProportion < screenProportion) {
//                    lp.height= screenHeight;
//                    lp.width = (int) ((float) screenHeight / videoProportion);
//                } else {
//                    lp.width = screenWidth;
//                    lp.height = (int) ((float) screenWidth * videoProportion);
//                }
//                loginBg.setLayoutParams(lp);
//            }
//        });
//        loginBg.start();

        if (loginStatus == 1) { //if logged in, proceed to app
            Intent homeActivity;
            if (role == 0) {
                homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeActivity);
            }
            else if (role == 1) {
                homeActivity = new Intent(getApplicationContext(), HomeIbuActivity.class);
                startActivity(homeActivity);
            }
            else if (role == 2) {
                homeActivity = new Intent(getApplicationContext(), HomeLansiaActivity.class);
                startActivity(homeActivity);
            }
            finish();
        }

        // handler for snackbar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Selamat datang di Smart Posyandu! Silahkan login terlebih dahulu", Snackbar.LENGTH_SHORT).show();
            }
        }, 1000);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameForm.getText().toString().length() == 0){
                    usernameLayout.setError("Username tidak boleh kosong");
                }
                else{
                    usernameLayout.setError(null);
                    if(passwordForm.getText().toString().length() == 0){
                        passwordLayout.setError("Password tidak boleh kosong");
                    }
                    else{
                        passwordLayout.setError(null);
                        login();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        forgotPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotPassActivity = new Intent(getApplicationContext(), ForgotpassActivity.class);
                startActivity(forgotPassActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loginBg.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        loginBg.stopPlayback();
    }

    public void login() {
        dialog.show();
        InterfaceApi loginApi = RetrofitClient.buildRetrofit().create(InterfaceApi.class);
        Call<UserLoginResponse> userLoginResponseCall = loginApi.loginUser(usernameForm.getText().toString(), passwordForm.getText().toString());
        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }

                if (response.code() == 200 && response.body().getStatusCode() == 200) {
                    // TODO implement cek semisal pass/email salah
                    int roleUser = Integer.parseInt(response.body().getUser().getRole());
                    Log.d("verif", response.body().getUser().getIsVerified());
                    if(Integer.parseInt(response.body().getUser().getIsVerified()) == 0) {
                        Intent notVerified = new Intent(getApplicationContext(), WaitForVerifiedActivity.class);
                        startActivity(notVerified);
                    }
                    else {
                        Log.d("masuksini", "masuk sini");
                        if(response.body().getFlagComplete() == 0) {
                            if(roleUser == 0) {
                                Intent registerDataAnak = new Intent(getApplicationContext(), RegisterDataAnakActivity.class);
                                registerDataAnak.putExtra("EMAILUSER", response.body().getUser().getEmail());
                                startActivity(registerDataAnak);
                            }
                            else if (roleUser == 1) {
                                Intent registerDataIbu = new Intent(getApplicationContext(), RegisterDataIbuActivity.class);
                                registerDataIbu.putExtra("EMAILUSER", response.body().getUser().getEmail());
                                startActivity(registerDataIbu);
                            }
                            else if (roleUser == 2) {
                                Intent registerDataLansia = new Intent(getApplicationContext(), RegisterDataLansiaActivity.class);
                                registerDataLansia.putExtra("EMAILUSER", response.body().getUser().getEmail());
                                startActivity(registerDataLansia);
                            }
                        }
                        else {
                            SharedPreferences.Editor loginPrefEditor = loginPreferences.edit();
                            SharedPreferences.Editor userPrefEditor = userPreferences.edit();
                            loginPrefEditor.putInt("login_status", 1);
                            loginPrefEditor.putString("token", response.body().getAccessToken());
                            loginPrefEditor.apply();

                            userPrefEditor.putString("email", response.body().getUser().getEmail());
                            userPrefEditor.putInt("posyandu", response.body().getPosyandu());
                            userPrefEditor.putString("nama_user", response.body().getNama());
                            userPrefEditor.putInt("bot_tele_banner", 0);
                            userPrefEditor.putInt("role", roleUser);
                            userPrefEditor.apply();

                            Intent homeActivity;

                            if (roleUser == 0) {
                                homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(homeActivity);
                            }
                            else if (roleUser == 1) {
                                homeActivity = new Intent(getApplicationContext(), HomeIbuActivity.class);
                                startActivity(homeActivity);
                            }
                            else if (roleUser == 2) {
                                homeActivity = new Intent(getApplicationContext(), HomeLansiaActivity.class);
                                startActivity(homeActivity);
                            }
                            finish();
                        }
                    }
                }

                else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Login gagal, silahkan cek email dan password atau coba lagi nanti", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), R.string.server_fail, Snackbar.LENGTH_SHORT).show();
            }
        });
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