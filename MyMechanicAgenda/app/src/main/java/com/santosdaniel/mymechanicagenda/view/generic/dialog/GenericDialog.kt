package com.santosdaniel.mymechanicagenda.view.generic.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.view.Window
import com.santosdaniel.mymechanicagenda.helper.StringHelper

/**
 * Generic dialog to be
 */
abstract class GenericDialog : DialogFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Fetch arguments from bundle and set title
        val title = arguments!!.getString(GenericDialogHelper.TITLE_KEY, StringHelper.EMPTY_STRING)

        val dialog = dialog
        dialog.setTitle(title)
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON)
    }
}
