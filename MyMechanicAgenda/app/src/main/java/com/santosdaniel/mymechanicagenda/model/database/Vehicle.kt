package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import com.santosdaniel.mymechanicagenda.presenter.vehicleDetails.BrandEnumConverter

/**
 * Used to represent one vehicle in the application
 */
@Table(database = MyMechanicDatabase::class)
class Vehicle : GenericEntity() {

    /**
     * List of costumers that the vehicle have associate
     */
    var costumers: List<Costumer>? = null

    /**
     * The brand of the vehicle
     */
    @Column(typeConverter = BrandEnumConverter::class)
    var brand: BrandEnum? = null
}
