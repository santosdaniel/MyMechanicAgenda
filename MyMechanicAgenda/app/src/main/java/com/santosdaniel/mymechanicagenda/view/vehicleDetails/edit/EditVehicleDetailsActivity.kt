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
     * Set the model in the fragments that make part of the activity
     */
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
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_vehicle_details_activity)
        setModel(savedInstanceState)
        setToolbarWithTitle(false)
    }




    companion object {
        const val MODEL_KEY = "model"
    }
}