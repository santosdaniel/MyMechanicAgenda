package com.santosdaniel.mymechanicagenda.view.reparationDetails

import com.santosdaniel.mymechanicagenda.model.database.Reparation

import java.io.Serializable

/**
 * Model used by the reparation activities
 */
class ReparationModel : Serializable {

    /**
     * Reference to the reparation to use in the activity
     */
    var reparation: Reparation? = null

    companion object {

        private const val serialVersionUID = -800311721185007153L
    }
}
