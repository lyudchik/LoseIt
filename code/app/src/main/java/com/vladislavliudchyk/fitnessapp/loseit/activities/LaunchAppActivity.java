package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vladislavliudchyk.fitnessapp.loseit.R;

public class LaunchAppActivity extends AppCompatActivity {

    private static final int MILLISECONDS_TO_DELAY = 1000;
    Handler handler = new Handler();

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_app);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchAppActivity.this, MainActivity.class));
            }
        }, MILLISECONDS_TO_DELAY);
    }
}
