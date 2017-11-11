package com.santosdaniel.mymechanicagenda.helper

/**
 * Container methods to the manage strings
 */
object StringHelper {
    var EMPTY_STRING = ""

    /**
     * Checks if a string is null or empty
     */
    fun isNullOrEmpty(str: String?) = (str == null) || (str.isEmpty())

    /**
     * Check that is string is neither null or empty
     */
    fun isNotNullOrEmpty(str: String?) = (!isNullOrEmpty(str))

}
