package com.santosdaniel.mymechanicagenda.view

import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.helper.ToolbarSearchViewHelper

import java.lang.ref.WeakReference

/**
 * Listener of the quit search button
 */
class SearchQuitOnClickListener

/**
 * Constructor to the listener
 *
 * @param toolbar       Reference to the toolbar view
 * @param searchSection Reference to the search section
 * @param model         Reference to the model supporting the search
 */
(toolbar: Toolbar, searchSection: View, searchView: SearchView, model: GenericActivityModel) : View.OnClickListener {

    companion object {
        private const val TAG = "SearchQuitOnClick"
    }

    private val toolbar = WeakReference(toolbar)
    private val searchSection = WeakReference(searchSection)
    private val searchView = WeakReference(searchView)
    private val model = WeakReference(model)

    /**
     * Submits a certain query
     *
     * @param query The query to submit
     */
    private fun submitQuery(query: String) {
        try {
            val searchView = this.searchView.get()
            searchView?.setQuery(query, true)
        } catch (e: Exception) {
            Log.d(TAG, "Was not possible to submit the query")
        }

    }

    /**
     * Called when a quit has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        val gModel = this.model.get()
        if (gModel != null) {
            gModel.isSearching = false
            submitQuery(StringHelper.EMPTY_STRING)
            ToolbarSearchViewHelper.showHideSearchView(gModel.isSearching, this.toolbar, this.searchSection)
        }
    }
}
