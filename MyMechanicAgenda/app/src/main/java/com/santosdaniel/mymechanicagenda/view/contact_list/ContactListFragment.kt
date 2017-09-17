package com.santosdaniel.mymechanicagenda.view.contact_list

import android.Manifest
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.enumerations.PermissionEnum
import com.santosdaniel.mymechanicagenda.enumerations.QueryEnum
import com.santosdaniel.mymechanicagenda.helper.PermissionsRequestHelper
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsAdapter
import com.santosdaniel.mymechanicagenda.presenter.contact_list.ContactsCursorLoader
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment

/**
 * A placeholder fragment containing a simple view.
 */
/**
 * Default constructor to the fragment
 */
class ContactListFragment : GenericRecycleViewFragment<ContactsAdapter>(), LoaderManager.LoaderCallbacks<Cursor> {

    // Called just before the Fragment displays its UI
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // Always call the super method first
        super.onActivityCreated(savedInstanceState)

        val contactsAdapter = ContactsAdapter(activity, this.lstResults!!, loadProgress!!)
        lstResults!!.adapter = contactsAdapter
        lstAdapter = contactsAdapter

        // Initializes the loader
        val emptyBundle = Bundle()
        loaderManager.initLoader(QueryEnum.ListContacts.ordinal, emptyBundle, this)
    }

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        this.lstResults = fragmentView.findViewById<View>(R.id.items_list) as RecyclerView
        this.loadProgress = fragmentView.findViewById<View>(R.id.load_progress) as ProgressBar


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        lstResults!!.setHasFixedSize(false)

        // use a linear layout manager
        this.lstLayoutManager = LinearLayoutManager(context)
        lstResults!!.layoutManager = lstLayoutManager
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater!!.inflate(R.layout.generic_list_content, container, false)
        bindViews(fragmentView)
        return fragmentView
    }


    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle): Loader<Cursor>? {
        this.lstAdapter!!.setIsLoading(true)
        if (id == QueryEnum.ListContacts.ordinal) {
            if (PermissionsRequestHelper.requestPermission(activity, Manifest.permission.READ_CONTACTS, PermissionEnum.ReadContacts.ordinal)) {
                return ContactsCursorLoader(context, args)
            } else {
                //Does not has enough permissions to get the contacts from the user
                this.lstAdapter!!.setDataSet(null)
                this.lstAdapter!!.notifyDataSetChanged()
                this.lstAdapter!!.setIsLoading(false)
                return null
            }
        } else {
            Log.e("cc", "Unexpected id: " + id)
            return null
        }
    }


    /**
     * onLoadFinished is called automatically when a Loader has finished its load
     *
     * @param loader
     * @param cursor
     */
    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor) {
        this.lstAdapter!!.setDataSet(cursor)
        this.lstAdapter!!.setIsLoading(false)
        this.lstAdapter!!.notifyDataSetChanged()
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {
        // Delete the reference to the existing Cursor
        //mCursorAdapter.swapCursor(null);
    }

    /**
     * Callback for the result from requesting permissions. This method
     *
     * @param requestCode  The request code passed in [.requestPermissions].
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     * which is either [android.content.pm.PackageManager.PERMISSION_GRANTED]
     * or [android.content.pm.PackageManager.PERMISSION_DENIED]. Never null.
     * @see .requestPermissions
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
