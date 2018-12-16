package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.RoomWarnings
import java.io.Serializable

/**
 * Entity mapped to table "CUSTOMER".
 */
@Entity(tableName = Customer.TABLE_NAME)
@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
data class Customer(

        /**
         * Full name of the costumer
         */
        @ColumnInfo(name = FULL_NAME_COLUMN_NAME)
        var fullName: String? = null,

        /**
         * Lookup of the costumer
         */
        @ColumnInfo(name = LOOKUP_COLUMN_NAME)
        var lookup: String? = null


) : Serializable, GenericEntity() {
    companion object {
        private const val serialVersionUID = 5271737151205573559L
        const val TABLE_NAME = "Customer"
        private const val FULL_NAME_COLUMN_NAME = "full_name"
        const val LOOKUP_COLUMN_NAME = "lookup"
    }
}
