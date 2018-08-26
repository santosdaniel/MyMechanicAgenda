package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.annotation.*
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.io.Serializable
import java.util.*

/**
 * Represents one reparation made over a certain vehicle
 */
@Table(database = MyMechanicDatabase::class)
class Reparation : GenericEntity(), Serializable {

    /**
     * The vehicle were was made the reparation
     */
    @ForeignKeyReference(columnName = VEHICLE_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Vehicle::class)
    var vehicle: Vehicle? = null

    /**
     * List of documents associated with reparation
     */
    @get:OneToMany(methods = [(OneToMany.Method.ALL)], variableName = DOCUMENTS_VARIABLE_NAME)
    var documents: List<Document>? = null

    /**
     * Date when the reparation was made
     */
    @Column(name = DATE_COLUMN_NAME)
    var date: Date? = null

    /**
     * Small description of the reparation made
     */
    @Column(name = DESCRIPTION_COLUMN_NAME)
    var description: String? = null

    /**
     * Small description of the reparation made
     */
    @Column(name = DURATION_VALUE_COLUMN_NAME)
    var durationValue: Float? = null

    /**
     * Unit of the duration of the reparation
     */
    @Column(name = DURATION_UNIT_COLUMN_NAME, typeConverter = TimeUnitConverter::class)
    var durationUnit: TimeUnit? = null

    companion object {
        private const val serialVersionUID = 2598511445037380404L
        private const val VEHICLE_COLUMN_NAME = "vehicle_id"
        private const val DOCUMENTS_VARIABLE_NAME = "documents"
        private const val DATE_COLUMN_NAME = "date"
        private const val DESCRIPTION_COLUMN_NAME = "description"
        private const val DURATION_VALUE_COLUMN_NAME = "duration_value"
        private const val DURATION_UNIT_COLUMN_NAME = "duration_unit"
    }
}
