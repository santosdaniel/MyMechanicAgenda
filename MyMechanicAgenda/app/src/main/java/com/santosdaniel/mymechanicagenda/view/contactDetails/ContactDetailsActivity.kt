package com.santosdaniel.mymechanicagenda.view.contactDetails

import android.os.Bundle

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericActivity
import com.santosdaniel.mymechanicagenda.view.contactList.ContactListModel

/**
 * Activity to present the contact with a list of vehicles
 */
class ContactDetailsActivity : GenericActivity<ContactListModel>() {


    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val model = ContactListModel()
            super.model = model
        }
    }

    /**
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        setToolbarWithTitle(R.string.phone_book, false)
        setModel(savedInstanceState)
    }

    companion object {
        val LOOKUP_KEY = "lookup"
        val TITLE_KEY = "title"
        val IMAGE_URI_KEY = "imageUri"
    }
}
