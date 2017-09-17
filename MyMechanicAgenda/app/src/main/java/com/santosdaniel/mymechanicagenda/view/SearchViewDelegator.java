package com.santosdaniel.mymechanicagenda.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.santosdaniel.mymechanicagenda.enumerations.QueryEnum;
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper;
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsCursorLoader;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 *  Delegates the search of content to the fragments passed
 */
public class SearchViewDelegator implements SearchView.OnQueryTextListener {

    private static final String TAG = SearchViewDelegator.class.getSimpleName();
    private final WeakReference<AppCompatActivity> activityRef;
    private final List<WeakReference<Fragment>> fragmentList;

    /**
     * Constructor of the delegator
     *
     * @param activity      Reference to the activity
     * @param fragmentList  List of fragments to notify
     */
    public SearchViewDelegator(AppCompatActivity activity, List<WeakReference<Fragment>> fragmentList) {
        this.activityRef = new WeakReference<>(activity);
        this.fragmentList = fragmentList;
    }

    /***
     * Submit a query to the fragments that implement the loaderCalls
     *
     * @param query The query submit by the user
     *
     * @return  If handle the query or not
     */
    private boolean submitQuery(String query) {
        if(ContainerHelper.INSTANCE.isNotEmpty(fragmentList)) {
            try {
                LoaderManager loaderManager = activityRef.get().getSupportLoaderManager();
                for (WeakReference<Fragment> fragmentWeakReference : fragmentList) {

                    try {
                        Fragment fragment = fragmentWeakReference.get();
                        if (fragment instanceof LoaderManager.LoaderCallbacks) {
                            LoaderManager.LoaderCallbacks callback = (LoaderManager.LoaderCallbacks) fragment;
                            // Starts the query
                            Bundle args = new Bundle();
                            args.putString(ContactsCursorLoader.NAME_ATTR, query);
                            loaderManager.restartLoader(QueryEnum.ListContacts.ordinal(), args, callback);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Something went wrong: " + e.getMessage());
                        return true;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Something went wrong: " + e.getMessage());
                return true;
            }
        }
        return false;
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
    @Override
    public boolean onQueryTextSubmit(String query) {
        return submitQuery(query);
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return submitQuery(newText);
    }
}
