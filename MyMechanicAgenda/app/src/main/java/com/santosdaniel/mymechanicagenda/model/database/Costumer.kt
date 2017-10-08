package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase

/**
 * Entity mapped to table "COSTUMER".
 */
@Table(database = MyMechanicDatabase::class)
class Costumer : GenericEntity() {


    /**
     * Full name of the costumer
     */
    @Column
    var fullName: String? = null

    /**
     * Lookup of the costumer
     */
    @Column
    var lookup: String? = null

    /**
     * List of vehicles that the costumer have associate
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = "vehicles")
    var vehicles: List<Vehicle>? = null
}
