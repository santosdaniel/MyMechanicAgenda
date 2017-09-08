package com.santosdaniel.mymechanicagenda.view;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Helper with methods to the views
 */
public class ViewHelper {

    public static void showSnackbar(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
