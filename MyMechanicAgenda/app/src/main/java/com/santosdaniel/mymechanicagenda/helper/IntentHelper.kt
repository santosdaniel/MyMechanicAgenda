package com.santosdaniel.mymechanicagenda.helper

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.View

import com.santosdaniel.mymechanicagenda.R
import java.io.Serializable

/**
 * Helper to manage intents of the application
 */
object IntentHelper {
    /**
     * Start a new activity if possible with material design transition
     *
     * @param activityOrigin The Activity whose window contains the shared elements.
     * @param sharedView     the view that is going to be shared between activities
     * @param intent         intent that is going to be used to launch the new activity
     */
    fun startNewActivity(activityOrigin: Activity, sharedView: View?, intent: Intent) =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //Simple start of activity
                activityOrigin.startActivity(intent)
            } else {
                //Start of activity with shared element transition
                val transitionName = activityOrigin.resources.getString(R.string.toolbar_transition)
                if (sharedView == null) {
                    activityOrigin.startActivity(intent)
                } else {
                    val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activityOrigin, sharedView, transitionName)
                    activityOrigin.startActivity(intent, transitionActivityOptions.toBundle())
                }
            }

    /**
     * Get a string from the intent
     *
     * @param intent    Intent from is to get the string
     * @param fieldKey  key of the field to read from the intent
     *
     *return The string that was put in the intent
     */
    fun getStringFromIntent(intent: Intent, fieldKey: String): String {
        val strValue = intent.getStringExtra(fieldKey)
        return if (TextUtils.isEmpty(strValue)) StringHelper.EMPTY_STRING else strValue
    }

    /**
     * Get a serializable from the intent
     *
     * @param intent    Intent from is to get the string
     * @param fieldKey  key of the field to read from the intent
     */
    fun getSerializable(intent: Intent, fieldKey: String): Serializable? {
        return try {
            intent.getSerializableExtra(fieldKey)
        } catch (e: Throwable) {
            null
        }
    }
}
