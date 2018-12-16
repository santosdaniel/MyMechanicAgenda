package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import java.util.*


/**
 * Association between contact and vehicle
 */
@Entity(tableName = CostumerVehicle.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Customer::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(CostumerVehicle.COSTUMER_ID_COLUMN_NAME),
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Vehicle::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(CostumerVehicle.VEHICLE_ID_COLUMN_NAME),
                    onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [Index(value = GenericEntity.ID_COLUMN_NAME),
            Index(value = CostumerVehicle.COSTUMER_ID_COLUMN_NAME),
            Index(value = CostumerVehicle.VEHICLE_ID_COLUMN_NAME)
        ]
)
data class CostumerVehicle(


        /**
         * Identifier of the costumer which the vehicle is associated
         */
        @ColumnInfo(name = COSTUMER_ID_COLUMN_NAME)
        var customer_id: Long,


        /**
         * Identifier of the vehicle
         */
        @ColumnInfo(name = VEHICLE_ID_COLUMN_NAME)
        var vehicle_id: Long,


        /**
         * Date when the entity was created
         */
        @ColumnInfo(name = FROM_COLUMN_NAME)
        var from: Date? = null,

        /**
         * Until when the association between costumer and vehicle exists
         * (Null means that is still valid)
         */
        @ColumnInfo(name = UNTIL_COLUMN_NAME)
        var until: Date? = null


) : GenericEntity() {
    companion object {
        const val TABLE_NAME = "CostumerVehicle"
        const val COSTUMER_ID_COLUMN_NAME = "costumer_id"
        const val VEHICLE_ID_COLUMN_NAME = "vehicle_id"
        private const val FROM_COLUMN_NAME = "from"
        private const val UNTIL_COLUMN_NAME = "until"
    }
}
