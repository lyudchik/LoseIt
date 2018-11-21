package com.vladislavliudchyk.fitnessapp.loseit.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vladislavliudchyk.fitnessapp.loseit.R;

/**
 * Class that launches application
 */
public class LaunchAppActivity extends AppCompatActivity {

    /**
     * Value of the delay in milliseconds
     */
    private static final int MILLISECONDS_TO_DELAY = 1000;
    /**
     * Value of the custom handler
     */
    Handler handler = new Handler();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_app);
    }

    /**
     * {@inheritDoc}
     */
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
