package com.sipanduteam.sipandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.app.ActivityCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sipanduteam.sipandu.MainActivity;
import com.sipanduteam.sipandu.R;

public class SplashActivity extends AppCompatActivity {

    TextView posyanduText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        hideSystemUI();

        MotionLayout motionLayout = findViewById(R.id.splash_motion_layout);
        posyanduText = findViewById(R.id.smart_posyandu_text);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        Handler splashHandler = new Handler();



        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//
//                String transitionName = "duar";
//
//                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, (View) icon, transitionName);
//                startActivity(i, transitionActivityOptions.toBundle());
                motionLayout.transitionToEnd();
            }
        }, 1000);

        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                togglePosyanduText();
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                startActivity(loginActivity);
//                finishAfterTransition();
                finish();
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
            }
        });

    }

    private void togglePosyanduText() {
        if (posyanduText.getVisibility() != View.VISIBLE) {
            posyanduText.setVisibility(View.VISIBLE);
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}