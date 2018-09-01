package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.io.Serializable

/**
 * Entity mapped to table "CUSTOMER".
 */
@Entity(tableName = Customer.TABLE_NAME)
@Table(database = MyMechanicDatabase::class)
data class Customer(

        /**
         * Full name of the costumer
         */
        @Column(name = FULL_NAME_COLUMN_NAME)
        @ColumnInfo(name = FULL_NAME_COLUMN_NAME)
        var fullName: String? = null,

        /**
         * Lookup of the costumer
         */
        @Column(name = LOOKUP_COLUMN_NAME)
        @ColumnInfo(name = LOOKUP_COLUMN_NAME)
        var lookup: String? = null,

        /**
         * List of vehicles that the costumer have associate
         */
        @get:OneToMany(methods = [(OneToMany.Method.ALL)], variableName = VEHICLES_VARIABLE_NAME)
        var vehicles: List<Vehicle>? = null


) : Serializable, GenericEntity() {
    companion object {
        private const val serialVersionUID = 5271737151205573559L
        const val TABLE_NAME = "Customer"
        private const val FULL_NAME_COLUMN_NAME = "full_name"
        private const val LOOKUP_COLUMN_NAME = "lookup"
        private const val VEHICLES_VARIABLE_NAME = "vehicles"

    }
}
