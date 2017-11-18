package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.ImageView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.IntentHelper
import com.santosdaniel.mymechanicagenda.helper.ViewHelper
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
import com.santosdaniel.mymechanicagenda.presenter.vehicleDetails.VehicleHelper
import com.santosdaniel.mymechanicagenda.view.GenericActivity
import com.santosdaniel.mymechanicagenda.view.IGenericStateView

/**
 * Activity to edit a vehicle
 */
class EditVehicleDetailsActivity : GenericActivity<Vehicle>() {

    /**
     * Reference to picture of the customer
     */
    private var customerPicture: ImageView? = null

    /**
     * Floating action that allows to add vehicles
     */
    private var addVehicle: FloatingActionButton? = null

    private fun setFragmentsModel(model: Vehicle) {
        val fullContentDetails = supportFragmentManager.findFragmentById(R.id.full_content_details_fragment)
        if (fullContentDetails is IGenericStateView<*>) {
            (fullContentDetails as IGenericStateView<Vehicle>).setState(model)
        }
    }

    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val model = IntentHelper.getSerializable(intent, MODEL_KEY)
            super.model = if (model == null) Vehicle() else (model as Vehicle)
        }
    }

    /**
     * Makes the bind between views and java variables
     */
    private fun bindViews() {
        //Makes the bind of the addVehicle
        this.addVehicle = findViewById(R.id.add_vehicle) as FloatingActionButton?
        this.customerPicture = findViewById(R.id.customer_picture) as ImageView?
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
        if (this.model != null) {
            val mModel = model as Vehicle
            title = VehicleHelper.brandAndModel(mModel, this)
            val photoUri = VehicleHelper.photoUri(mModel)
            ViewHelper.loadImageOrDefault(this, photoUri, R.mipmap.add_car, this.customerPicture)
        }
    }


    companion object {
        const val MODEL_KEY = "model"
    }
}