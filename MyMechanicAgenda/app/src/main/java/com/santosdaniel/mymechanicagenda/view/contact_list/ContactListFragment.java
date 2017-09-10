package com.santosdaniel.mymechanicagenda.view.contact_list;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.helper.PermissionsRequestHelper;
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsAdapter;
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsCursorLoader;
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment;

import android.provider.ContactsContract.Contacts;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.widget.AdapterView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactListFragment extends GenericRecycleViewFragment<ContactsAdapter> implements LoaderManager.LoaderCallbacks<Cursor> {

    @SuppressWarnings("FieldCanBeLocal")
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    Contacts.DISPLAY_NAME_PRIMARY :
                    Contacts.DISPLAY_NAME
    };



    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;


    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private final static int[] TO_IDS = {
            android.R.id.text1
    };



    // Define global mutable variables
    // Define a ListView object
    ListView mContactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long mContactId;
    // The contact's LOOKUP_KEY
    String mContactKey;
    // A content URI for the selected contact
    Uri mContactUri;

    // Called just before the Fragment displays its UI
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Always call the super method first
        super.onActivityCreated(savedInstanceState);

        ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), this.lstResults, loadProgress);
        lstResults.setAdapter(contactsAdapter);
        lstAdapter = contactsAdapter;

        //...
        // Initializes the loader
        getLoaderManager().initLoader(0, null, this);
    }

        /**
         * Default constructor to the fragment
         */
    public ContactListFragment() {
    }

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private void bindViews(View fragmentView) {
        this.lstResults = (RecyclerView) fragmentView.findViewById(R.id.items_list);
        this.loadProgress = (ProgressBar) fragmentView.findViewById(R.id.load_progress);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        lstResults.setHasFixedSize(false);

        // use a linear layout manager
        this.lstLayoutManager = new LinearLayoutManager(getContext());
        lstResults.setLayoutManager(lstLayoutManager);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.generic_list_content, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }



    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        //mSelectionArgs[0] = "%" + mSearchString + "%";

        if (PermissionsRequestHelper.requestPermission(getActivity(), Manifest.permission.READ_CONTACTS, MY_PERMISSIONS_REQUEST_READ_CONTACTS)) {
            // Starts the query
            return new ContactsCursorLoader(getContext());
        }
        else {
            return null;
        }

    }


    /**
     * onLoadFinished is called automatically when a Loader has finished its load
     *
     * @param loader
     * @param cursor
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.lstAdapter.setDataSet(cursor);
        this.lstAdapter.setIsLoading(false);
        this.lstAdapter.notifyDataSetChanged();
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        //mCursorAdapter.swapCursor(null);
    }
}
