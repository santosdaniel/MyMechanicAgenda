package com.santosdaniel.mymechanicagenda.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.santosdaniel.mymechanicagenda.helper.ToolbarSearchViewHelper;

import java.lang.ref.WeakReference;

/**
 * Listener of the quit search button
 */
public class SearchQuitOnClickListener implements View.OnClickListener {

    private final WeakReference<Toolbar> toolbar;
    private final WeakReference<View> searchSection;
    private final WeakReference<GenericActivityModel> model;

    /**
     * Constructor to the listener
     *
     * @param toolbar       Reference to the toolbar view
     * @param searchSection Reference to the search section
     * @param model         Reference to the model supporting the search
     */
    public SearchQuitOnClickListener(@NonNull Toolbar toolbar, @NonNull View searchSection, @NonNull GenericActivityModel model) {
        this.toolbar = new WeakReference<>(toolbar);
        this.searchSection = new WeakReference<>(searchSection);
        this.model = new WeakReference<>(model);
    }

    /**
     * Called when a quit has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        GenericActivityModel gModel = this.model.get();
        if(gModel != null) {
            gModel.isSearching = false;
            ToolbarSearchViewHelper.showHideSearchView(gModel.isSearching, this.toolbar, this.searchSection);
        }
    }
}
