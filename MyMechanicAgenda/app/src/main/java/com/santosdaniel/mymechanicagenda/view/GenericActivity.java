package com.santosdaniel.mymechanicagenda.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity that has all the logic used by all the activities of the application
 */
public abstract class GenericActivity extends AppCompatActivity {

    /**
     * Prevent the crash due to animations when the user go to the activity back.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finishAfterTransition() {
        finish();
    }
}

