package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase

/**
 * Entity mapped to table "CUSTOMER".
 */
@Table(database = MyMechanicDatabase::class)
data class Customer(

        /**
         * Full name of the costumer
         */
        @Column(name = FULL_NAME_COLUMN_NAME)
        var fullName: String? = null,

        /**
         * Lookup of the costumer
         */
        @Column(name = LOOKUP_COLUMN_NAME)
        var lookup: String? = null,

        /**
         * List of vehicles that the costumer have associate
         */
        @get:OneToMany(methods = [(OneToMany.Method.ALL)], variableName = VEHICLES_VARIABLE_NAME)
        var vehicles: List<Vehicle>? = null


) : GenericEntity() {
    companion object {
        const val FULL_NAME_COLUMN_NAME = "full_name"
        const val LOOKUP_COLUMN_NAME = "lookup"
        const val VEHICLES_VARIABLE_NAME = "vehicles"
    }
}
