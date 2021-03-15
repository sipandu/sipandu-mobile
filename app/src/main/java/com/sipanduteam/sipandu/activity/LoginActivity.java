package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.sipanduteam.sipandu.R;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    //TODO untuk loginnya tolong di catet juga nanti role nya, pake sharedpref aja, biar nanti gampang untuk nentuin setiap viewnya yg mana,
    // catet juga informasi penting usernya kek nama, rolenya,dll di sharedpref nya nanti

    TextInputLayout usernameForm;
    TextInputLayout passwordForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // handler for snackbar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Selamat datang di Sipandu! Silahkan login terlebih dahulu", Snackbar.LENGTH_SHORT).show();
            }
        }, 1000);

        //login button
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeActivity);
                finish();
            }
        });

        //register button
        Button registerButton = findViewById(R.id.reg_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        //forgot pass button
        Button forgotPassButton = findViewById(R.id.forgot_pass_button);
        forgotPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotPassActivity = new Intent(getApplicationContext(), ForgotpassActivity.class);
                startActivity(forgotPassActivity);
            }
        });

        usernameForm = findViewById(R.id.username_form);
        passwordForm = findViewById(R.id.password_form);
    }
}