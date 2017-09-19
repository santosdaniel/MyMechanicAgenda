package com.santosdaniel.mymechanicagenda.helper

import android.database.Cursor

/**
 * Helper to the containers of the application
 */
object ContainerHelper {

    /**
     * Checks if an array is null or empty
     *
     * @param array Array to check if it is empty
     * @param <T>   Type of the elements tha compose the array
     * @return False the array is not empty
     * True the array is null or empty
    </T> */
    fun <T> isEmpty(array: Array<T>?): Boolean = array == null || array.isEmpty()

    /**
     * Checks if an array is not empty
     *
     * @param array Array to check
     * @param <T>   Type of the elements tha compose the array
     * @return False the array is null or empty
     * True the array is not empty
    </T> */
    fun <T> isNotEmpty(array: Array<T>): Boolean = !isEmpty(array)

    /**
     * Checks if a list is null or empty
     *
     * @param list List to check if it is empty
     * @param <T>  Type of the elements tha compose the list
     * @return False the list is not empty
     * True the list is null or empty
    </T> */
    fun <T> isEmpty(list: List<T>?): Boolean = list == null || list.isEmpty()

    /**
     * Checks if a list is not empty
     *
     * @param list List to check
     * @param <T>  Type of the elements tha compose the list
     * @return False the list is null or empty
     * True the list is not empty
    </T> */
    fun <T> isNotEmpty(list: List<T>?): Boolean = !isEmpty(list)

    /**
     * Checks if a cursor is null or empty
     *
     * @param cursor Cursor to check if it is empty
     * @return False the cursor is not empty
     * True the cursor is null or empty
     */
    fun isEmpty(cursor: Cursor?): Boolean = cursor == null || cursor.count == 0

    /**
     * Checks if a list is not empty
     *
     * @param cursor Cursor to check
     * @return False the cursor is null or empty
     * True the cursor is not empty
     */
    fun isNotEmpty(cursor: Cursor?): Boolean = !isEmpty(cursor)

    /**
     * Verifies if a certain object is null
     *
     * @obj The object to check if it is null
     */
    private fun <T> isNull(obj : T?) = (obj == null)

    /**
     * Verifies if a certain object is null null
     *
     * @obj The object to check if it is not null
     */
    fun <T> isNotNull(obj: T?) = (!ContainerHelper.isNull(obj))
}
