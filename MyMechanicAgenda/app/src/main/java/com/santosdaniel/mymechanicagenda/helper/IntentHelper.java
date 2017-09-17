package com.santosdaniel.mymechanicagenda.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.santosdaniel.mymechanicagenda.R;

/**
 * Helper to manage intents of the application
 */
public class IntentHelper {
    /**
     * Start a new activity if possible with material design transition
     *
     * @param activityOrigin The Activity whose window contains the shared elements.
     * @param sharedView     the view that is going to be shared between activities
     * @param intent         intent that is going to be used to launch the new activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void startNewActivity(Activity activityOrigin, View sharedView, Intent intent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //Simple start of activity
            activityOrigin.startActivity(intent);
        } else {
            //Start of activity with shared element transition
            String transitionName = activityOrigin.getResources().getString(R.string.toolbar_transition);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activityOrigin, sharedView, transitionName);
            activityOrigin.startActivity(intent, transitionActivityOptions.toBundle());
        }
    }
}
