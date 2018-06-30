package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.presenter.contactList.ContactsAdapter
import com.santosdaniel.mymechanicagenda.view.generic.GenericRecycleViewProvider
import com.santosdaniel.mymechanicagenda.view.generic.dialog.GenericDialog
import com.santosdaniel.mymechanicagenda.view.generic.dialog.GenericDialogHelper

/**
 * Dialog that allows the user select the brand of the vehicle
 */

class BrandPickerDialog : GenericDialog() {

    private var brandList: GenericRecycleViewProvider<ContactsAdapter>? = null
    private var btnCancel: Button? = null
    private var btnOk: Button? = null


    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        val brandList = GenericRecycleViewProvider<ContactsAdapter>()
        brandList.bindViews(fragmentView, R.id.items_list, R.id.load_progress)
        // use a linear layout manager
        brandList.setLayoutManager(LinearLayoutManager(context))
        this.brandList = brandList
        //Bind the buttons
        this.btnCancel = fragmentView.findViewById(R.id.btn_cancel)
        this.btnOk = fragmentView.findViewById(R.id.btn_ok)
    }


    /**
     * Set the actions that is going to take when the user clicks in the buttons
     */
    private fun setActions() {
        this.btnCancel!!.setOnClickListener(View.OnClickListener {
            this.dismiss()
        })
        this.btnOk!!.setOnClickListener(View.OnClickListener {
            this.dismiss()
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.brand_picker_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setActions()
    }

    // Called just before the Fragment displays its UI
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // Always call the super method first
        super.onActivityCreated(savedInstanceState)

        /*
        if (this.brandList != null) {
            val brandList = this.brandList!!
            val lstResults = brandList.lstResults!!
            val loadProgress = brandList.loadProgress!!
            val contactsAdapter = ContactsAdapter(activity!!, lstResults, loadProgress)
            lstResults.adapter = contactsAdapter
            brandList.lstAdapter = contactsAdapter

            // Initializes the loader
            val emptyBundle = Bundle()
            // loaderManager.initLoader(QueryEnum.ListContacts.ordinal, emptyBundle, this)
        }
        */
    }

    companion object {
        fun newInstance(context: Context): DialogFragment {
            val dialogFragment = BrandPickerDialog()
            val title: String = context.getString(R.string.select_brand)
            GenericDialogHelper.setBundleArguments(dialogFragment, title)
            return dialogFragment
        }
    }
}

