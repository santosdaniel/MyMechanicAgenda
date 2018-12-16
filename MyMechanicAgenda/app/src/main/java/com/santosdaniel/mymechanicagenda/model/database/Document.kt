package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import java.io.Serializable
import java.util.*

/**
 * Represents one document of the vehicle or of the reparation
 */
@Entity(tableName = Document.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Vehicle::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(Document.VEHICLE_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            ),
            ForeignKey(
                    entity = Reparation::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(Document.REPARATION_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            )
        ],
        indices = [
            Index(value = GenericEntity.ID_COLUMN_NAME),
            Index(value = Document.VEHICLE_ID_COLUMN_NAME),
            Index(value = Document.REPARATION_ID_COLUMN_NAME)
        ]
)
data class Document(

        /**
         * Type of document to use
         */
        @ColumnInfo(name = Document.TYPE_NAME)
        var type: DocumentTypeEnum,

        /**
         * The vehicle which the document belongs
         */
        @ColumnInfo(name = Document.VEHICLE_ID_COLUMN_NAME)
        var vehicle_id: Long? = null,

        /**
         * The reparation which the document belongs
         */
        @ColumnInfo(name = Document.REPARATION_ID_COLUMN_NAME)
        var reparation_id: Long? = null,

        /**
         * Title of the document
         */
        @ColumnInfo(name = TITLE_COLUMN_NAME)
        var title: String? = null,


        /**
         * Description of document
         */
        @ColumnInfo(name = DESCRIPTION_COLUMN_NAME)
        var description: String? = null,

        /**
         * Date of the document
         */
        @ColumnInfo(name = DATE_COLUMN_NAME)
        var date: Date? = null


) : GenericEntity(), Serializable {
    /**
     * Define the constants used by the main entity
     */
    companion object {
        const val TABLE_NAME = "Document"
        private const val serialVersionUID = -1530458964982407070L
        const val VEHICLE_ID_COLUMN_NAME = "vehicle_id"
        const val REPARATION_ID_COLUMN_NAME = "reparation_id"
        private const val TITLE_COLUMN_NAME = "title"
        private const val DESCRIPTION_COLUMN_NAME = "description"
        private const val DATE_COLUMN_NAME = "date"
        private const val TYPE_NAME = "type"
    }
}
