package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.NotNull
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.util.*


/**
 * Association between contact and vehicle
 */
/**
 * Default constructor of the association
 */
@Table(database = MyMechanicDatabase::class)
class CostumerVehicle {

    /**
     * Identifier of the association in database
     */
    @PrimaryKey
    var id: Long? = null

    /**
     * Identifier of the costumer which the vehicle is associated
     */
    @Column(name = COSTUMER_ID_COLUMN_NAME)
    var contactId: Long = 0

    /**
     * Identifier of the vehicle
     */
    @Column(name = VEHICLE_ID_COLUMN_NAME)
    var vehicleId: Long = 0


    /**
     * Date when the entity was created
     */
    @NotNull
    @Column(name = FROM_COLUMN_NAME)
    var from: Date? = null

    /**
     * Until when the association between costumer and vehicle exists
     * (Null means that is still valid)
     */
    @Column(name = UNTIL_COLUMN_NAME)
    var until: Date? = null

    companion object {

        const val COSTUMER_ID_COLUMN_NAME = "costumerId"
        const val VEHICLE_ID_COLUMN_NAME = "vehicleId"
        const val FROM_COLUMN_NAME = "from"
        const val UNTIL_COLUMN_NAME = "until"
    }


}
