package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.ViewHelper
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
import com.santosdaniel.mymechanicagenda.presenter.customerDetails.CustomerRepository
import com.santosdaniel.mymechanicagenda.presenter.vehicleDetails.VehicleHelper

import java.text.DateFormat
import java.util.Date

/**
 * Fragment that contains the details of the vehicle to edit
 */

class EditVehicleDetailsFragment : Fragment() {

    /*List of UI variables*/

    /**
     * Reference to picture of the vehicle
     */
    private var vehiclePicture: ImageView? = null

    private var editText: EditText? = null



    /**
     * Makes the bind between views and kotlin variables
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        //Makes the bind of the add reparation
        this.vehiclePicture = fragmentView.findViewById(R.id.tool_bar_picture)
        this.editText = fragmentView.findViewById(R.id.editTextNote)
    }

    /**
     * Set the actions that is going to make when the user clicks int the elements of the UI
     */
    private fun setViewActions() {
        editText!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addVehicle()
                return@OnEditorActionListener true
            }
            false
        })
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.generic_progress_list_fragment, container, false)
        bindViews(fragmentView)
        setViewActions()

        return fragmentView
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
        val noteText = editText!!.text.toString()
        editText!!.setText("")

        val df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
        val comment = "Added on " + df.format(Date())
    }
}
