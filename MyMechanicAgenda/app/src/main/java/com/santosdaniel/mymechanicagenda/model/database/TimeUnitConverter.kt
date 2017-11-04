package com.santosdaniel.mymechanicagenda.model.database

import com.raizlabs.android.dbflow.converter.TypeConverter

/**
 * Converts a time unit in a such way that can be saved in database
 */
class TimeUnitConverter : TypeConverter<String, TimeUnit>() {


    /**
     * Converts the enum in a such way that can be saved in database
     *
     * @param entityProperty The entity to use
     * @return The value to be persisted in database
     */
    override fun getDBValue(entityProperty: TimeUnit): String {
        return entityProperty.name
    }

    /**
     * Creates the entity with value that exists from database
     *
     * @param databaseValue String that was persisted in database
     * @return The enum to use
     */
    override fun getModelValue(databaseValue: String): TimeUnit {
        return TimeUnit.valueOf(databaseValue)
    }
}