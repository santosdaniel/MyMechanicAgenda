package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.*
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.util.*


/**
 * Association between contact and vehicle
 */
@Table(database = MyMechanicDatabase::class)
class CostumerVehicle : GenericEntity() {


    /**
     * Identifier of the costumer which the vehicle is associated
     */
    @NotNull
    @ForeignKeyReference(columnName = COSTUMER_ID_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Customer::class)
    var customer: Customer? = null

    /**
     * Identifier of the vehicle
     */
    @NotNull
    @ForeignKeyReference(columnName = VEHICLE_ID_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Vehicle::class)
    var vehicle: Vehicle? = null


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

        const val COSTUMER_ID_COLUMN_NAME = "costumer_id"
        const val VEHICLE_ID_COLUMN_NAME = "vehicle_id"
        const val FROM_COLUMN_NAME = "from"
        const val UNTIL_COLUMN_NAME = "until"
    }
}
