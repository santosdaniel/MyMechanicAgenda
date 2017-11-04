package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.converter.TypeConverter


/**
 * Converts a brand in a such way that can be saved in database
 */
class BrandEnumConverter : TypeConverter<String, BrandEnum>() {


    /**
     * Converts the brand in a such way that can be saved in database
     *
     * @param entityProperty The entity to use
     * @return The value to be persisted in database
     */
    override fun getDBValue(entityProperty: BrandEnum): String {
        return entityProperty.name
    }

    /**
     * Creates the entity with value that exists from database
     *
     * @param databaseValue String that was persisted in database
     * @return The brand to use
     */
    override fun getModelValue(databaseValue: String): BrandEnum {
        return BrandEnum.valueOf(databaseValue)
    }
}