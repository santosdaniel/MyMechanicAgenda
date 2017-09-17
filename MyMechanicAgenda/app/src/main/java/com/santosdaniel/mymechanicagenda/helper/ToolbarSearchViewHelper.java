package com.santosdaniel.mymechanicagenda.helper;

import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.ref.WeakReference;

import static android.view.View.*;

/**
 * Helper with methods common to the search view
 */
public class ToolbarSearchViewHelper {

    /**
     * Shows or hides the search view elements
     *
     * @param isSearching   Flag indicating if its searching or not
     * @param toolbar       Reference to the toolbar of the application
     * @param searchSection Place that wraps the search elements
     */
    public static void showHideSearchView(boolean isSearching, Toolbar toolbar, View searchSection) {
        if (isSearching) {
            UIHelper.setVisibility(GONE, toolbar);
            UIHelper.setVisibility(View.VISIBLE, searchSection);
        } else {

            UIHelper.setVisibility(GONE, searchSection);
            UIHelper.setVisibility(View.VISIBLE, toolbar);
        }
    }

    /**
     * Shows or hides the search view elements
     *
     * @param isSearching   Flag indicating if its searching or not
     * @param toolbar       Reference to the toolbar of the application
     * @param searchSection Place that wraps the search elements
     */
    public static void showHideSearchView(boolean isSearching, WeakReference<Toolbar> toolbar, WeakReference<View> searchSection) {
        try {
            showHideSearchView(isSearching, toolbar.get(), searchSection.get());

        } catch (Exception e) {

        }
    }
}
