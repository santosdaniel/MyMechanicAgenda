package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.converter.TypeConverter

/**
 * Converts a document type in a such way that can be saved in database
 */
class DocumentTypeConverter : TypeConverter<String, DocumentTypeEnum>() {


    /**
     * Converts the enum in a such way that can be saved in database
     *
     * @param entityProperty The entity to use
     *
     * @return The value to be persisted in database
     */
    override fun getDBValue(entityProperty: DocumentTypeEnum): String {
        return entityProperty.name
    }

    /**
     * Creates the entity with value that exists from database
     *
     * @param databaseValue String that was persisted in database
     *
     * @return The enum to use
     */
    override fun getModelValue(databaseValue: String): DocumentTypeEnum {
        return DocumentTypeEnum.valueOf(databaseValue)
    }
}