package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.annotation.*
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.io.Serializable
import java.util.*

/**
 * Represents one document of the vehicle or of the reparation
 */
@Table(database = MyMechanicDatabase::class)
class Document : GenericEntity(), Serializable {

    /**
     * Type of document to use
     */
    @Column(typeConverter = DocumentTypeConverter::class)
    var type: DocumentTypeEnum? = null

    /**
     * The vehicle which the document belongs
     */
    @ForeignKeyReference(columnName = VEHICLE_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Vehicle::class)
    var vehicle: Vehicle? = null

    /**
     * The reparation which the document belongs
     */
    @ForeignKeyReference(columnName = REPAIR_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Reparation::class)
    var reparation: Reparation? = null

    /**
     * Title of the document
     */
    @Column(name = TITLE_COLUMN_NAME)
    var title: String? = null


    /**
     * Description of document
     */
    @Column(name = DESCRIPTION_COLUMN_NAME)
    var description: String? = null

    /**
     * Date of the document
     */
    @Column(name = DATE_COLUMN_NAME)
    var date: Date? = null

    /**
     * List of vehicles that the costumer have associate
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = PHOTOS_VARIABLE_NAME)
    var photos: MutableList<DocumentPhoto>? = null

    /**
     * Define the constants used by the main entity
     */
    companion object {
        private const val serialVersionUID = -1530458964982407070L
        private const val VEHICLE_COLUMN_NAME = "vehicle"
        private const val REPAIR_COLUMN_NAME = "reparation"
        private const val TITLE_COLUMN_NAME = "title"
        private const val DESCRIPTION_COLUMN_NAME = "description"
        private const val DATE_COLUMN_NAME = "date"
        private const val PHOTOS_VARIABLE_NAME = "photos"
    }
}
