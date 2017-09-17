package com.santosdaniel.mymechanicagenda.view.contact_list;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.enumerations.PermissionEnum;
import com.santosdaniel.mymechanicagenda.enumerations.QueryEnum;
import com.santosdaniel.mymechanicagenda.helper.PermissionsRequestHelper;
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsAdapter;
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsCursorLoader;
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactListFragment extends GenericRecycleViewFragment<ContactsAdapter> implements LoaderManager.LoaderCallbacks<Cursor> {

    // Called just before the Fragment displays its UI
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Always call the super method first
        super.onActivityCreated(savedInstanceState);

        ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), this.lstResults, loadProgress);
        lstResults.setAdapter(contactsAdapter);
        lstAdapter = contactsAdapter;

        // Initializes the loader
        getLoaderManager().initLoader(QueryEnum.ListContacts.ordinal(), null, this);
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
        this.lstAdapter.setIsLoading(true);
        if(id == QueryEnum.ListContacts.ordinal()) {
            if (PermissionsRequestHelper.INSTANCE.requestPermission(getActivity(), Manifest.permission.READ_CONTACTS, PermissionEnum.ReadContacts.ordinal())) {
                return new ContactsCursorLoader(getContext(), args);
            } else {
                //Does not has enough permissions to get the contacts from the user
                this.lstAdapter.setDataSet(null);
                this.lstAdapter.notifyDataSetChanged();
                this.lstAdapter.setIsLoading(false);
                return null;
            }
        } else {
            Log.e("cc", "Unexpected id: " + id);
            return  null;
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

    /**
     * Callback for the result from requesting permissions. This method
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *                     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
