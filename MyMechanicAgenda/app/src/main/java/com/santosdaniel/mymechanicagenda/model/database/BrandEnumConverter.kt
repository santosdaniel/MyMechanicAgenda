package com.santosdaniel.mymechanicagenda.model.database


import android.arch.persistence.room.TypeConverter


/**
 * Converts a brand in a such way that can be saved in database
 */
class BrandEnumConverter {


    /**
     * Converts the brand in a such way that can be saved in database
     *
     * @param entityProperty The entity to use
     * @return The value to be persisted in database
     */
    @TypeConverter
    fun getDBValue(entityProperty: BrandEnum): String {
        return entityProperty.name
    }

    /**
     * Creates the entity with value that exists from database
     *
     * @param databaseValue String that was persisted in database
     * @return The brand to use
     */
    @TypeConverter
    fun getModelValue(databaseValue: String): BrandEnum {
        return BrandEnum.valueOf(databaseValue)
    }
}