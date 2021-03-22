package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.api.BaseApi;
import com.sipanduteam.sipandu.api.RetrofitClient;
import com.sipanduteam.sipandu.model.user.UserLoginResponse;

import java.util.Calendar;
import java.util.Date;

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
    private ProgressDialog dialog;
    private int loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inflate everything
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.reg_button);
        forgotPassButton = findViewById(R.id.forgot_pass_button);
        usernameLayout = findViewById(R.id.username_form);
        passwordLayout = findViewById(R.id.password_form);
        usernameForm = findViewById(R.id.username_text_field);
        passwordForm = findViewById(R.id.password_text_field);

        dialog = new ProgressDialog(this);

        loginPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        loginStatus = loginPreferences.getInt("login_status", 0);

        if (loginStatus == 1) { //if logged in, proceed to app
            Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeActivity);
            finish();
        }

        // handler for snackbar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Selamat datang di Posyandu 5.0! Silahkan login terlebih dahulu", Snackbar.LENGTH_SHORT).show();
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

    public void login() {
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
        BaseApi loginApi = RetrofitClient.buildRetrofit().create(BaseApi.class);
        Call<UserLoginResponse> userLoginResponseCall = loginApi.loginUser(usernameForm.getText().toString(), passwordForm.getText().toString());
        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                SharedPreferences.Editor loginPrefEditor = loginPreferences.edit();
                SharedPreferences.Editor userPrefEditor = userPreferences.edit();
                loginPrefEditor.putInt("login_status", 1);
                loginPrefEditor.putString("token", response.body().getAccessToken());
                loginPrefEditor.apply();

                userPrefEditor.putString("email", response.body().getUser().getEmail());
                userPrefEditor.apply();

                Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Something went duar meledak yey api nya meledak", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}