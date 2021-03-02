package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.fragment.forgotpass.ForgotMethod;
import com.sipanduteam.sipandu.fragment.forgotpass.TelegramMethod;
import com.sipanduteam.sipandu.fragment.home.BerandaFragment;

public class ForgotpassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        getSupportFragmentManager().beginTransaction().replace(R.id.forgot_pass_container, new ForgotMethod()).commit();

        Button backToLogin = findViewById(R.id.back_login_button);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}