package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.NotNull
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.MyMechanicAgendaApp

import java.util.Date


/**
 * Association between contact and vehicle
 */
/**
 * Default constructor of the association
 */
@Table(database = MyMechanicAgendaApp::class)
class CostumerVehicle {

    /**
     * Identifier of the association in database
     */
    @PrimaryKey
    private val id: Long? = null

    /**
     * Identifier of the costumer which the vehicle is associated
     */
    @Column(name = COSTUMER_ID_COLUMN_NAME)
    private val contactId: Long = 0

    /**
     * Identifier of the vehicle
     */
    @Column(name = VEHICLE_ID_COLUMN_NAME)
    private val vehicleId: Long = 0


    /**
     * Date when the entity was created
     */
    @NotNull
    @Column(name = FROM_COLUMN_NAME)
    private val from: Date? = null

    /**
     * Until when the association between costumer and vehicle exists
     * (Null means that is still valid)
     */
    @Column(name = UNTIL_COLUMN_NAME)
    private val until: Date? = null

    companion object {

        val COSTUMER_ID_COLUMN_NAME = "costumerId"
        val VEHICLE_ID_COLUMN_NAME = "vehicleId"
        val FROM_COLUMN_NAME = "from"
        val UNTIL_COLUMN_NAME = "until"
    }


}
