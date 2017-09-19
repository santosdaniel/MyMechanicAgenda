package com.santosdaniel.mymechanicagenda.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log

import com.santosdaniel.mymechanicagenda.enumerations.QueryEnum
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsCursorLoader

import java.lang.ref.WeakReference

/**
 * Delegates the search of content to the fragments passed
 */
class SearchViewDelegator
/**
 * Constructor of the delegator
 *
 * @param activity      Reference to the activity
 * @param fragmentList  List of fragments to notify
 */
(activity: AppCompatActivity, private val fragmentList: List<WeakReference<Fragment>>?) : SearchView.OnQueryTextListener {
    private val activityRef: WeakReference<AppCompatActivity>

    init {
        this.activityRef = WeakReference(activity)
    }

    /***
     * Submit a query to the fragments that implement the loaderCalls
     *
     * @param query The query submit by the user
     *
     * @return  If handle the query or not
     */
    private fun submitQuery(query: String): Boolean {
        if (ContainerHelper.isNotEmpty(fragmentList)) {
            try {
                val activity = activityRef.get()
                if(activity != null) {
                    val loaderManager = activity.supportLoaderManager
                    for (fragmentWeakReference in (fragmentList!!)) {

                        try {
                            val fragment = fragmentWeakReference.get()
                            if (fragment is LoaderManager.LoaderCallbacks<*>) {
                                val callback = fragment as LoaderManager.LoaderCallbacks<*>
                                // Starts the query
                                val args = Bundle()
                                args.putString(ContactsCursorLoader.NAME_ATTR, query)
                                loaderManager.restartLoader(QueryEnum.ListContacts.ordinal, args, callback)
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Something went wrong: " + e.message)
                            return true
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Something went wrong: " + e.message)
                return true
            }

        }
        return false
    }

    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    override fun onQueryTextSubmit(query: String): Boolean = submitQuery(query)

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    override fun onQueryTextChange(newText: String): Boolean = submitQuery(newText)

    companion object {

        private val TAG = SearchViewDelegator::class.java.simpleName
    }
}
