package com.santosdaniel.mymechanicagenda.helper

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Helper with methods to the views
 */
object ViewHelper {

    fun showSnackbar(view: View) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}
