package com.sipanduteam.sipandu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.sipanduteam.sipandu.R;

import java.util.Random;

import com.sipanduteam.sipandu.BuildConfig;

public class SplashActivity extends AppCompatActivity {

    TextView posyanduText, versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        hideSystemUI();
        MotionLayout motionLayout = findViewById(R.id.splash_motion_layout);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(motionLayout);

//        int random;
//        Random rnd = new Random();
//        random = rnd.nextInt(3);
//        Log.d("random", String.valueOf(random));
//        constraintSet.clear(R.id.transition_view, ConstraintSet.BOTTOM);
//        constraintSet.clear(R.id.transition_view, ConstraintSet.END);
//
//        if (random == 0) {
//            constraintSet.connect(R.id.transition_view, ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM, 0);
//            constraintSet.connect(R.id.imageView,ConstraintSet.END, motionLayout.getId(),ConstraintSet.END,0);
//            constraintSet.applyTo(motionLayout);
//        }
//        else if (random == 1) {
//            constraintSet.connect(R.id.transition_view, ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP, 0);
//            constraintSet.connect(R.id.imageView,ConstraintSet.END, motionLayout.getId(),ConstraintSet.END,0);
//            constraintSet.applyTo(motionLayout);
//        }
//
//        else if (random == 2) {
//            constraintSet.connect(R.id.transition_view, ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP, 0);
//            constraintSet.connect(R.id.imageView,ConstraintSet.START, motionLayout.getId(),ConstraintSet.START,0);
//            constraintSet.applyTo(motionLayout);
//        }
//
//        else {
//            constraintSet.connect(R.id.transition_view, ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM, 0);
//            constraintSet.connect(R.id.imageView,ConstraintSet.START, motionLayout.getId(),ConstraintSet.START,0);
//            constraintSet.applyTo(motionLayout);
//        }


        posyanduText = findViewById(R.id.smart_posyandu_text);
        versionText = findViewById(R.id.sipandu_text);
        versionText.setText(getResources().getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME);


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

        Handler finishHandler = new Handler();



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
                finishHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(loginActivity);
//                finishAfterTransition();
                        finish();
                    }
                }, 300);;
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