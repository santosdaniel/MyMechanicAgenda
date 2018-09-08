package com.santosdaniel.mymechanicagenda.view.contactList

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.enumerations.PermissionEnum
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.helper.PermissionsRequestHelper
import com.santosdaniel.mymechanicagenda.presenter.contactList.ContactsAdapter
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment
import com.santosdaniel.mymechanicagenda.view.ISearchListener
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
/**
 * Default constructor to the fragment
 */
class ContactListFragment : GenericRecycleViewFragment<ContactsAdapter>(), ISearchListener {
    /**
     * Called when the user submit a query
     */
    override fun submitQuery(query: String?) {
        viewModel?.loadContacts(this.activity!!.contentResolver, query)

        this.startListening()
    }

    private var viewModel: ContactListViewModel? = null

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
     * @param inflater  The inflater of the view
     * @param container The container where is to inflate the fragment
     * @param savedInstanceState    The state previously saved
     *
     * @return  The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.generic_list_content, container, false)
        bindViews(fragmentView)
        return fragmentView
    }

    private fun startListening() {
        viewModel?.contactsList?.observe(this, Observer {
            this.lstAdapter?.setIsLoading(false)
            this.lstAdapter?.submitList(it)
            //contactsEmpty.visibility = if (adapter.itemCount > 0) {
            //    View.GONE
            //} else {
            //    View.VISIBLE
            //}
            Log.d("danie", "success$it")
        })
    }

    // Called just before the Fragment displays its UI
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // Always call the super method first
        super.onActivityCreated(savedInstanceState)

        val contactsAdapter = ContactsAdapter(activity!!, this.lstResults!!, loadProgress!!)
        lstResults!!.adapter = contactsAdapter
        lstAdapter = contactsAdapter

        // Initializes the loader
        this.viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)


        val refActivity = WeakReference<FragmentActivity>(activity)
        return if (PermissionsRequestHelper.requestPermission(refActivity, this,
                        Manifest.permission.READ_CONTACTS, PermissionEnum.ReadContacts.ordinal)) {
            this.submitQuery(null)
        } else {
            //Does not has enough permissions to get the contacts from the user
            //TODO: does not has permissions
        }
    }


    /**
     *  Set a certain cursor int the adapter
     *
     * @cursor The cursor to set
     */
    protected fun setCursorInAdapter() {
        if (ContainerHelper.isNotNull(this.lstAdapter)) {
            this.lstAdapter?.setIsLoading(false)
            this.lstAdapter?.notifyDataSetChanged()
        }
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

        Log.d(TAG, "Test")
        //TODO
    }

    companion object {

        private const val TAG = "ContactListFragment"
    }
}
