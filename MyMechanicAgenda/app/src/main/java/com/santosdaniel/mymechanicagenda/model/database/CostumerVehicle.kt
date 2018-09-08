package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.util.*


/**
 * Association between contact and vehicle
 */
/*
@Entity(tableName = ,
        foreignKeys = {
            @ForeignKey(
                    entity = Customer.class,
                            parentColumns = "id",
            childColumns = "userId",
            onDelete = ForeignKey.CASCADE
            )},
        indices = { @Index(value = "id")}
)

@Entity(tableName = CostumerVehicle.TABLE_NAME,
        foreignKeys = {
            @ForeignKey(
                    entity = Customer.class,
                            parentColumns = "id",
            childColumns = "userId",
            onDelete = ForeignKey.CASCADE
            )},
        indices = { @Index(value = "id")}
)
*/

@Entity(tableName = CostumerVehicle.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Customer::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(CostumerVehicle.COSTUMER_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            )
        ],
        indices = [Index(value = GenericEntity.ID_COLUMN_NAME)]
)
@com.raizlabs.android.dbflow.annotation.Table(database = MyMechanicDatabase::class)
data class CostumerVehicle(


        /**
         * Identifier of the costumer which the vehicle is associated
         */
        @com.raizlabs.android.dbflow.annotation.NotNull
        @com.raizlabs.android.dbflow.annotation.ForeignKeyReference(columnName = COSTUMER_ID_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
        @com.raizlabs.android.dbflow.annotation.ForeignKey(tableClass = Customer::class)
        var customer: Customer? = null,


        /**
         * Identifier of the vehicle
         */
        @com.raizlabs.android.dbflow.annotation.NotNull
        @com.raizlabs.android.dbflow.annotation.ForeignKeyReference(columnName = VEHICLE_ID_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
        @com.raizlabs.android.dbflow.annotation.ForeignKey(tableClass = Vehicle::class)
        var vehicle: Vehicle? = null,


        /**
         * Date when the entity was created
         */
        @com.raizlabs.android.dbflow.annotation.NotNull
        @com.raizlabs.android.dbflow.annotation.Column(name = FROM_COLUMN_NAME)
        @ColumnInfo(name = FROM_COLUMN_NAME)
        var from: Date? = null,

        /**
         * Until when the association between costumer and vehicle exists
         * (Null means that is still valid)
         */
        @com.raizlabs.android.dbflow.annotation.Column(name = UNTIL_COLUMN_NAME)
        @ColumnInfo(name = UNTIL_COLUMN_NAME)
        var until: Date? = null


) : GenericEntity() {
    companion object {
        const val TABLE_NAME = "CostumerVehicle"
        const val COSTUMER_ID_COLUMN_NAME = "costumer_id"
        private const val VEHICLE_ID_COLUMN_NAME = "vehicle_id"
        private const val FROM_COLUMN_NAME = "from"
        private const val UNTIL_COLUMN_NAME = "until"
    }
}
