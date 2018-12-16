package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.*
import java.io.Serializable

/**
 * Represents one photo of the document.
 * A document can have multiple photos
 */
@Entity(tableName = DocumentPhoto.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Customer::class,
                    parentColumns = arrayOf(GenericEntity.ID_COLUMN_NAME),
                    childColumns = arrayOf(DocumentPhoto.DOCUMENT_ID_COLUMN_NAME),
                    onDelete = ForeignKey.NO_ACTION
            )
        ],
        indices = [
            Index(value = GenericEntity.ID_COLUMN_NAME),
            Index(value = DocumentPhoto.DOCUMENT_ID_COLUMN_NAME)
        ])
@SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
data class DocumentPhoto(

        /**
         * The document which the photo belongs
         */
        @ColumnInfo(name = DOCUMENT_ID_COLUMN_NAME)
        var document_id: Long? = null,

        /**
         * Path to the photo
         */
        @ColumnInfo(name = PATH_COLUMN_NAME)
        var path: String? = null


) : GenericEntity(), Serializable {
    companion object {
        const val TABLE_NAME = "DocumentPhoto"
        private const val serialVersionUID = -4633240669554940410L
        const val DOCUMENT_ID_COLUMN_NAME = "document_ID"
        private const val PATH_COLUMN_NAME = "path"
    }
}
