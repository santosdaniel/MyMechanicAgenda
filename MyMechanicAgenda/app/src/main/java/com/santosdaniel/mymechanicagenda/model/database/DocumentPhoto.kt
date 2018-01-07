package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.annotation.*
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.io.Serializable

/**
 * Represents one photo of the document.
 * A document can have multiple photos
 */
@Table(database = MyMechanicDatabase::class)
class DocumentPhoto : GenericEntity(), Serializable {

    /**
     * The document which the photo belongs
     */
    @ForeignKeyReference(columnName = DOCUMENT_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = Document::class)
    var document: Document? = null

    /**
     * Path to the photo
     */
    @NotNull
    @Column(name = PATH_COLUMN_NAME)
    var path: String? = null

    companion object {
        private const val serialVersionUID = -4633240669554940410L
        private const val DOCUMENT_COLUMN_NAME = "document"
        private const val PATH_COLUMN_NAME = "path"
    }
}
