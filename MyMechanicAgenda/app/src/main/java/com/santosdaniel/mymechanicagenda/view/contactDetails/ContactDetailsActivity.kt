package com.santosdaniel.mymechanicagenda.view.contactDetails

import android.os.Bundle

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.IntentHelper
import com.santosdaniel.mymechanicagenda.view.GenericActivity

/**
 * Activity to present the contact with a list of vehicles
 */
class ContactDetailsActivity : GenericActivity<ContactDetailsModel>() {



    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val model = ContactDetailsModel()
            model.lookupId = IntentHelper.getStringFromIntent(intent, LOOKUP_KEY)
            model.title = IntentHelper.getStringFromIntent(intent, TITLE_KEY)
            model.imageUri = IntentHelper.getStringFromIntent(intent, IMAGE_URI_KEY)

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
        setModel(savedInstanceState)
        setToolbarWithTitle(false)
    }

    public override fun onResume() {
        super.onResume()

        title = model!!.title
    }



    companion object {
        val LOOKUP_KEY = "lookup"
        val TITLE_KEY = "title"
        val IMAGE_URI_KEY = "imageUri"
    }
}
