package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import java.util.*


/**
 * Contains a set of fields that are going to be keep by all the entities in database
 */
/**
 * Default constructor of the generic entity
 */
abstract class GenericEntity(

        /**
         * Identifier of the entity in database
         */
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,

        /**
         * Date when the entity was created
         */
        @ColumnInfo(name = CREATED_DATE_COLUMN_NAME)
        var createdDate: Date? = null,

        /**
         * Date when the entity was last modified
         * Ambiguous note: Created = Consider as modified too
         */
        @ColumnInfo(name = MODIFIED_DATE_COLUMN_NAME)
        var modifiedDate: Date? = null
) {
    /**
     * Define the constants used by the main entity
     */
    companion object {
        const val ID_COLUMN_NAME = "id"
        const val CREATED_DATE_COLUMN_NAME = "created_date"
        const val MODIFIED_DATE_COLUMN_NAME = "modified_date"
    }
}
