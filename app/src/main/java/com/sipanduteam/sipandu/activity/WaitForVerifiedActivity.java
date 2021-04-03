package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sipanduteam.sipandu.R;

public class WaitForVerifiedActivity extends AppCompatActivity {

    private ImageView checkImage;
    private AnimatedVectorDrawable checkAnim;
    private Button backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_verified);

        backToLogin = findViewById(R.id.register_complete_button);
        checkImage = findViewById(R.id.register_check_image);
        Drawable d = checkImage.getDrawable();
        if (d instanceof AnimatedVectorDrawable) {
            checkAnim = (AnimatedVectorDrawable) d;
            checkAnim.start();
        }
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}