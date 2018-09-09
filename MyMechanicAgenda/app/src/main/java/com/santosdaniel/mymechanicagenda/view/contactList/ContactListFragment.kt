package com.santosdaniel.mymechanicagenda.view.contactList

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.enumerations.PermissionEnum
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.helper.PermissionsRequestHelper
import com.santosdaniel.mymechanicagenda.helper.UIHelper
import com.santosdaniel.mymechanicagenda.presenter.contactList.ContactsAdapter
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment
import com.santosdaniel.mymechanicagenda.view.ISearchListener
import com.santosdaniel.mymechanicagenda.view.generic.ViewHelper
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
/**
 * Default constructor to the fragment
 */
class ContactListFragment : GenericRecycleViewFragment<ContactsAdapter>(), ISearchListener {

    private var permissionsWarning: TextView? = null

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
    override fun bindViews(fragmentView: View) {
        super.bindViews(fragmentView)

        this.permissionsWarning = fragmentView.findViewById(R.id.permissions_warning)

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
        val fragmentView = inflater.inflate(R.layout.contact_list_fragment, container, false)
        bindViews(fragmentView)
        return fragmentView
    }

    private fun startListening() {
        viewModel?.contactsList?.observe(this, Observer {
            this.lstAdapter?.setIsLoading(false)
            this.lstAdapter?.submitList(it)
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
        val requestContactsDialogTitle = getString(R.string.request_contacts_dialog_title)
        val requestContactsDialogDescription = getString(R.string.request_contacts_dialog_description)

        val canReadContacts = PermissionsRequestHelper.requestPermission(
                refActivity, this,
                Manifest.permission.READ_CONTACTS, PermissionEnum.ReadContacts.ordinal,
                requestContactsDialogTitle, requestContactsDialogDescription
        )

        showPermissionsWarning(!canReadContacts)
        if (canReadContacts) {
            this.submitQuery(null)
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

        if (ContainerHelper.isNotEmpty(permissions)) {
            for (i in permissions.indices) {
                val permission = permissions[i]
                val wasGrant = grantResults[i] == PackageManager.PERMISSION_GRANTED
                when (permission) {
                    Manifest.permission.READ_CONTACTS -> handleContactPermission(wasGrant)
                }
            }
        }
    }

    private fun showPermissionsWarning(visible: Boolean) = if(visible) {
        UIHelper.setVisibility(View.GONE, this.lstResults, this.loadProgress)
        UIHelper.setVisibility(View.VISIBLE, this.permissionsWarning)
    } else {
        UIHelper.setVisibility(View.VISIBLE, this.lstResults, this.loadProgress)
        UIHelper.setVisibility(View.GONE, this.permissionsWarning)
    }

    private fun handleContactPermission(wasGrant: Boolean) {
        showPermissionsWarning(!wasGrant)
        if (wasGrant) {
            this.submitQuery(null)
        }
    }

    companion object {

        private const val TAG = "ContactListFragment"
    }
}
