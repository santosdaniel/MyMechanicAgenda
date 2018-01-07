package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericStateFragment
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.VehicleDetailsModel
import java.text.DateFormat
import java.util.*

/**
 * Fragment that contains the details of the vehicle to edit
 */

class EditVehicleDetailsFragment : GenericStateFragment<VehicleDetailsModel>() {

    /*List of UI variables*/
    private var vinNumber: EditText? = null
    private var brand: TextView? = null
    private var vehicleModel: EditText? = null
    private var year: EditText? = null


    /**
     * Makes the bind between views and kotlin variables
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        //Makes the bind of the fragment
        this.vinNumber = fragmentView.findViewById(R.id.vinNumberValue)
        this.brand = fragmentView.findViewById(R.id.vinNumberValue)
        this.vehicleModel = fragmentView.findViewById(R.id.vinNumberValue)
        this.year = fragmentView.findViewById(R.id.vinNumberValue)
    }


    /**
     * Set the actions that is going to make when the user clicks int the elements of the UI
     */
    private fun setViewActions() {
        /*
        editText!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addVehicle()
                return@OnEditorActionListener true
            }
            false
        })
        */
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.edit_vehicle_details_fragment, container, false)
        bindViews(fragmentView)
        setViewActions()

        return fragmentView
    }


    /**
     * Load the VIN number of the vehicle
     */
    private fun loadVinNumber(state: VehicleDetailsModel) {}

    /**
     * Load the brand of the vehicle
     */
    private fun loadBrand(state: VehicleDetailsModel) {}

    /**
     * Load the model of the vehicle
     */
    private fun loadVehicleModel(state: VehicleDetailsModel) {}

    /**
     * Load the year of the vehicle
     */
    private fun loadYearModel(state: VehicleDetailsModel) {}

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: VehicleDetailsModel) {
        super._state = state
        loadVinNumber(state)
        loadBrand(state)
        loadVehicleModel(state)
        loadYearModel(state)
    }


    /**
     * Called when the activity is resuming
     */
    public override fun onResume() {
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

    private fun addVehicle() {
        /*val noteText = editText!!.text.toString()
        editText!!.setText("")
        */
        val df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
        val comment = "Added on " + df.format(Date())
    }
}
