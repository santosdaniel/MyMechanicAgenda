package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.*
import java.io.Serializable

/**
 * Used to represent one vehicle in the application
 */
@Entity(tableName = Vehicle.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Vehicle::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(Vehicle.MODEL_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            )
        ],
        indices = [Index(value = GenericEntity.ID_COLUMN_NAME),
            Index(value = Vehicle.MODEL_ID_COLUMN_NAME)
        ])
@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
data class Vehicle(


        /**
         * Vehicle identification number
         */
        @ColumnInfo(name = VI_NUMBER_COLUMN_NAME)
        var vinNumber: String? = null,

        /**
         * The brand of the vehicle
         *
         */
        @ColumnInfo(name = BRAND_COLUMN_NAME)
        var brand: BrandEnum? = null,

        /**
         * The model of the vehicle to use
         */
        @ColumnInfo(name = MODEL_ID_COLUMN_NAME)
        var model_id: Long? = null,


        /**
         * Indicates the yer of the vehicle
         */
        @ColumnInfo(name = YEAR_COLUMN_NAME)
        var year: Int? = null


) : GenericEntity(), Serializable {
    companion object {
        const val TABLE_NAME = "Vehicle"
        private const val serialVersionUID = -4913897499672647187L
        private const val VI_NUMBER_COLUMN_NAME = "vi_number"
        private const val BRAND_COLUMN_NAME = "brand"
        const val MODEL_ID_COLUMN_NAME = "model_id"
        private const val YEAR_COLUMN_NAME = "year"
    }
}
