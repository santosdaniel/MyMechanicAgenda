package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase

import java.io.Serializable

/**
 * Represents one model of a certain vehicle
 */
@Table(database = MyMechanicDatabase::class)
class VehicleModel : GenericEntity(), Serializable {

    /**
     * The brand of the vehicle
     *
     */
    @Column(name = BRAND_COLUMN_NAME, typeConverter = BrandEnumConverter::class)
    var brand: BrandEnum? = null

    /**
     * Name of the model
     */
    @Column(name = NAME_COLUMN_NAME)
    var name: String? = null


    companion object {
        private const val serialVersionUID = 3961910546080080719L
        private const val BRAND_COLUMN_NAME = "brand"
        private const val NAME_COLUMN_NAME = "name"
    }
}
