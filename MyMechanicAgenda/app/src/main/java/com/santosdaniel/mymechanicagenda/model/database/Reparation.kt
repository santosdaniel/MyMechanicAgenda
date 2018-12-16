package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.*
import java.io.Serializable
import java.util.*

/**
 * Represents one reparation made over a certain vehicle
 */
@Entity(tableName = Reparation.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Vehicle::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(Reparation.VEHICLE_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            )
        ],
        indices = [
            Index(value = GenericEntity.ID_COLUMN_NAME),
            Index(value = Reparation.VEHICLE_ID_COLUMN_NAME)
        ]
)
@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
data class Reparation(

        /**
         * The vehicle were was made the reparation
         */
        @ColumnInfo(name = VEHICLE_ID_COLUMN_NAME)
        var vehicle_id: Long? = null,


        /**
         * Date when the reparation was made
         */
        @ColumnInfo(name = DATE_COLUMN_NAME)
        var date: Date? = null,

        /**
         * Small description of the reparation made
         */
        @ColumnInfo(name = DESCRIPTION_COLUMN_NAME)
        var description: String? = null,

        /**
         * Small description of the reparation made
         */
        @ColumnInfo(name = DURATION_VALUE_COLUMN_NAME)
        var durationValue: Float? = null,

        /**
         * Unit of the duration of the reparation
         */
        @TypeConverters(TimeUnitConverter::class)
        @ColumnInfo(name = DURATION_UNIT_COLUMN_NAME)
        var durationUnit: TimeUnit? = null


) : GenericEntity(), Serializable {
    companion object {
        const val TABLE_NAME = "Reparation"
        private const val serialVersionUID = 2598511445037380404L
        const val VEHICLE_ID_COLUMN_NAME = "vehicle_id"
        private const val DATE_COLUMN_NAME = "date"
        private const val DESCRIPTION_COLUMN_NAME = "description"
        private const val DURATION_VALUE_COLUMN_NAME = "duration_value"
        private const val DURATION_UNIT_COLUMN_NAME = "duration_unit"
    }
}
