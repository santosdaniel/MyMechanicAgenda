package com.santosdaniel.mymechanicagenda.helper

import android.support.v7.widget.Toolbar
import android.view.View
import android.view.View.GONE
import java.lang.ref.WeakReference

/**
 * Helper with methods common to the search view
 */
object ToolbarSearchViewHelper {

    /**
     * Shows or hides the search view elements
     *
     * @param isSearching   Flag indicating if its searching or not
     * @param toolbar       Reference to the toolbar of the application
     * @param searchSection Place that wraps the search elements
     */
    fun showHideSearchView(isSearching: Boolean, toolbar: Toolbar, searchSection: View) =
            if (isSearching) {
                UIHelper.setVisibility(GONE, toolbar)
                UIHelper.setVisibility(View.VISIBLE, searchSection)
            } else {
                UIHelper.setVisibility(GONE, searchSection)
                UIHelper.setVisibility(View.VISIBLE, toolbar)
            }

    /**
     * Shows or hides the search view elements
     *
     * @param isSearching   Flag indicating if its searching or not
     * @param toolbar       Reference to the toolbar of the application
     * @param searchSection Place that wraps the search elements
     */
    fun showHideSearchView(isSearching: Boolean, toolbar: WeakReference<Toolbar>, searchSection: WeakReference<View>) {
        try {
            val tb = toolbar.get()
            val sSection = searchSection.get()
            if ((tb != null) && (sSection != null)) {
                showHideSearchView(isSearching, tb, sSection)
            }
        } catch (e: Exception) {
        }
    }
}
