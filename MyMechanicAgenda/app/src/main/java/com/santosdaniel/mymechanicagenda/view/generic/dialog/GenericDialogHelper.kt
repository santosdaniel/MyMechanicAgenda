package com.santosdaniel.mymechanicagenda.view.generic.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment

/**
 * Helper to generic dialog
 */
object GenericDialogHelper {
    const val TITLE_KEY = "title"


    fun setBundleArguments(dialogFragment: DialogFragment, title: String) {
        val args = Bundle()
        args.putString(TITLE_KEY, title)
        dialogFragment.arguments = args
    }
}

