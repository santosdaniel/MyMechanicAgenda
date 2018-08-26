package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.Entity
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase

import java.io.Serializable

/**
 * Represents one model of a certain vehicle
 */
@Entity(tableName = VehicleModel.TABLE_NAME)
@Table(database = MyMechanicDatabase::class)
data class VehicleModel(

        /**
         * The brand of the vehicle
         *
         */
        @Column(name = BRAND_COLUMN_NAME, typeConverter = BrandEnumConverter::class)
        var brand: BrandEnum? = null,

        /**
         * Name of the model
         */
        @Column(name = NAME_COLUMN_NAME)
        var name: String? = null

) : GenericEntity(), Serializable {
    companion object {
        const val TABLE_NAME = "VehicleModel"
        private const val serialVersionUID = 3961910546080080719L
        private const val BRAND_COLUMN_NAME = "brand"
        private const val NAME_COLUMN_NAME = "name"
    }
}
