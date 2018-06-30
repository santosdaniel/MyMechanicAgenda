package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericStateFragment
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.VehicleDetailsModel
import java.text.DateFormat
import java.util.*

/**
 * Fragment that contains the details of the vehicle to edit
 */

class EditVehicleSaveFragment : GenericStateFragment<VehicleDetailsModel>() {

    /*List of UI variables*/

    private var saveBtn: Button? = null


    /**
     * Makes the bind between views and kotlin variables
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        //Makes the bind of the fragment
        this.saveBtn = fragmentView.findViewById(R.id.saveBtn)
    }

    /**
     * Set the actions that is going to make when the user clicks int the elements of the UI
     */
    private fun setViewActions() {
        this.saveBtn!!.setOnClickListener { _ -> submitVehicle() }
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(R.layout.edit_vehicle_details_save_fragment, container, false)
        bindViews(fragmentView)
        setViewActions()

        return fragmentView
    }

    /**
     * Called when the activity is resuming
     */
    override fun onResume() {
        super.onResume()
        /*
        if (this.model != null) {
            val mModel = model as Vehicle
            title = VehicleHelper.brandAndModel(mModel, this)
            val photoUri = VehicleHelper.photoUri(mModel)
            ViewHelper.loadImageOrDefault(this, photoUri, R.mipmap.vehicle, this.vehiclePicture)
        }
        */
    }

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: VehicleDetailsModel) {
        super._state = state
    }

    private fun submitVehicle() {
        /*val noteText = editText!!.text.toString()
        editText!!.setText("")
        */
        val df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
        val comment = "Added on " + df.format(Date())
    }
}

