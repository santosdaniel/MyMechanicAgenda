package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.os.Bundle
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.IntentHelper
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
import com.santosdaniel.mymechanicagenda.view.GenericFragmentedActivity
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.VehicleDetailsModel

/**
 * Activity to edit a vehicle
 */
class EditVehicleDetailsActivity : GenericFragmentedActivity<VehicleDetailsModel>() {


    /**
     * Set the model in the fragments that make part of the activity
     */
    private fun setFragmentsModel(model: VehicleDetailsModel) {
        val fragmentIds = arrayOf(R.id.picture_fragment, R.id.full_content_details_fragment, R.id.save_content_fragment)
        super.setFragmentsModel(fragmentIds, model)
    }


    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            var model = IntentHelper.getSerializable(intent, MODEL_KEY)
            super.model = if (model == null) VehicleDetailsModel() else (model as VehicleDetailsModel)
            if ((super.model!!).vehicle == null) {
                (super.model!!).vehicle = Vehicle()
            }
            setTitle(super.model!!)
            setFragmentsModel(super.model!!)
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

    /**
     * Sets the title of the activity
     */
    private fun setTitle(model: VehicleDetailsModel) {
        this.title = if ((model.vehicle == null) || (model.vehicle!!.id == null) || (model.vehicle!!.model == null)) {
            this.resources.getString(R.string.add_vehicle)
        } else {
            this.resources.getString(R.string.edit_specific_vehicle, model.vehicle!!.model!!.name)
        }
    }

    public override fun onResume() {
        super.onResume()
        setTitle(model!!)

    }


    companion object {
        const val MODEL_KEY = "model"
    }
}