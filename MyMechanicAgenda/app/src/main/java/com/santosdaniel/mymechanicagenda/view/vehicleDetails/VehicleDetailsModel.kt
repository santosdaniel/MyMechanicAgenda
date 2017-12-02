package com.santosdaniel.mymechanicagenda.view.vehicleDetails

import com.santosdaniel.mymechanicagenda.model.database.Vehicle

import java.io.Serializable

/**
 * Model used by the vehicle activities
 */
class VehicleDetailsModel : Serializable {

    /**
     * Reference to the vehicle to use in the activity
     */
    val vehicle: Vehicle? = null

    companion object {

        private const val serialVersionUID = 6002410669814149028L
    }


}
