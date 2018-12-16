package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.RoomWarnings

import java.io.Serializable

/**
 * Represents one model of a certain vehicle
 */
@Entity(tableName = VehicleModel.TABLE_NAME)
@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
data class VehicleModel(

        /**
         * The brand of the vehicle
         *
         */
        @ColumnInfo(name = BRAND_COLUMN_NAME)
        var brand: BrandEnum? = null,

        /**
         * Name of the model
         */
        @ColumnInfo(name = NAME_COLUMN_NAME)
        var name: String? = null

) : GenericEntity(), Serializable {
    companion object {
        const val TABLE_NAME = "VehicleModel"
        private const val serialVersionUID = 3961910546080080719L
        private const val BRAND_COLUMN_NAME = "brand"
        private const val NAME_COLUMN_NAME = "name"
    }
}
