package com.santosdaniel.mymechanicagenda.view.customerDetails

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.ImageView

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.IntentHelper
import com.santosdaniel.mymechanicagenda.helper.ViewHelper
import com.santosdaniel.mymechanicagenda.view.GenericActivity
import com.santosdaniel.mymechanicagenda.view.IGenericStateView
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit.EditVehicleDetailsActivity

/**
 * Activity to present the contact with a list of vehicles
 */
class CustomerDetailsActivity : GenericActivity<CustomerDetailsModel>() {

    /**
     * Reference to picture of the customer
     */
    private var customerPicture: ImageView? = null

    /**
     * Floating action that allows to add vehicles
     */
    private var addVehicle: FloatingActionButton? = null

    private fun setFragmentsModel(model: CustomerDetailsModel) {
        val fullContentDetails = supportFragmentManager.findFragmentById(R.id.full_content_details_fragment)
        if(fullContentDetails is IGenericStateView<*>) {
            (fullContentDetails as IGenericStateView<CustomerDetailsModel>).setState(model)
        }
    }

    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val model = CustomerDetailsModel()
            model.lookupId = IntentHelper.getStringFromIntent(intent, LOOKUP_KEY)
            model.title = IntentHelper.getStringFromIntent(intent, TITLE_KEY)
            model.imageUri = IntentHelper.getStringFromIntent(intent, IMAGE_URI_KEY)

            super.model = model
            setFragmentsModel(model)
        }
    }

    /**
     * Makes the bind between views and java variables
     */
    private fun bindViews() {
        //Makes the bind of the addVehicle
        this.addVehicle = findViewById(R.id.add_vehicle) as FloatingActionButton?
        this.customerPicture = findViewById(R.id.tool_bar_picture) as ImageView?
        this.addVehicle?.setOnClickListener({
            val intent = Intent(this, EditVehicleDetailsActivity::class.java)
            IntentHelper.startNewActivity(this, this.addVehicle!!, intent)
        })
    }

    /**
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_details_activity)
        setModel(savedInstanceState)
        setToolbarWithTitle(false)
        bindViews()
    }

    /**
     * Called when the activity is resuming
     */
    public override fun onResume() {
        super.onResume()
        if(this.model != null) {
            val mModel = model as CustomerDetailsModel
            title = mModel.title
            ViewHelper.loadImageOrDefault(this, mModel.imageUri, R.mipmap.person, this.customerPicture)
            mModel.imageUri
        }

    }


    companion object {
        const val LOOKUP_KEY = "lookup"
        const val TITLE_KEY = "title"
        const val IMAGE_URI_KEY = "imageUri"
    }
}
