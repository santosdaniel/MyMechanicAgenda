package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericStateFragment
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.VehicleDetailsModel

/**
 * Activity to present the contact with a list of vehicles
 */
class EditVehiclePictureFragment : GenericStateFragment<VehicleDetailsModel>() {


    /*UI variables*/

    /**
     * Reference to picture of the vehicle
     */
    private var vehiclePicture: ImageView? = null


    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        this.vehiclePicture = fragmentView.findViewById(R.id.tool_bar_picture)
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.collapsing_image_toolbar, container, false)
        bindViews(fragmentView)
        return fragmentView
    }


    /**
     * Load the image of the customer
     */
    private fun loadPicture(state: VehicleDetailsModel) {
        Log.d(TAG, state.toString())
    }

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: VehicleDetailsModel) {
        super._state = state
        loadPicture(state)
    }


    /**
     * Called when information of the fragment needs to be saved
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState, VEHICLE_DATA_KEY)
    }

    /**
     * Called when the activity is created and the fragment is recreated
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.loadStateFromBundle(savedInstanceState, VEHICLE_DATA_KEY)
    }

    companion object {
        val TAG = "EditVehiclePicFragment"
        val VEHICLE_DATA_KEY = "vehicleData"
    }
}
