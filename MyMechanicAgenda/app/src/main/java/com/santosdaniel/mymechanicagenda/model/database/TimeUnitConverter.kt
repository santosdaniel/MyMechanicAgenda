package com.santosdaniel.mymechanicagenda.model.database

import android.arch.persistence.room.TypeConverter

/**
 * Converts a time unit in a such way that can be saved in database
 */
class TimeUnitConverter {


    /**
     * Converts the enum in a such way that can be saved in database
     *
     * @param entityProperty The entity to use
     * @return The value to be persisted in database
     */
    @TypeConverter
    fun getDBValue(entityProperty: TimeUnit): String {
        return entityProperty.name
    }

    /**
     * Creates the entity with value that exists from database
     *
     * @param databaseValue String that was persisted in database
     * @return The enum to use
     */
    @TypeConverter
    fun getModelValue(databaseValue: String): TimeUnit {
        return TimeUnit.valueOf(databaseValue)
    }
}