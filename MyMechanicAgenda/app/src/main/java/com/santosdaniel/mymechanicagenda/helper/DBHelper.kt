package com.santosdaniel.mymechanicagenda.helper

/**
 * Helper to the database elements
 */
object DBHelper {

    /**
     * @param id    Identifier to check if it is valid
     *
     * @return False is not a valid identifier to the database
     *         True  is a valid identifier to the database
     */
    fun validId(id: Long?) : Boolean = ((id != null) && (id > 0))
}