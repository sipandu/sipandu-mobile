package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.sipanduteam.sipandu.R;

public class LoginActivity extends AppCompatActivity {

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

        // login role dropdown
        String[] loginRole = new String[] {("kohi wangy wangy"), ("kano wangy wangy"), ("parerun wangy wangy")};
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.login_role_dropdown_menu_item, loginRole);
        AutoCompleteTextView loginRoleDropdown = findViewById(R.id.login_role_dropdown);
        loginRoleDropdown.setAdapter(adapter);
        loginRoleDropdown.setInputType(0);

        // TODO biar ga bisa ke tulis manual di role select nya
        loginRoleDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg1.getApplicationWindowToken(), 0);
            }
        });

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
    }
}