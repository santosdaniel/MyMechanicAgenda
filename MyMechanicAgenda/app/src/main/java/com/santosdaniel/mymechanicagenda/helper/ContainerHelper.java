package com.santosdaniel.mymechanicagenda.helper;

import android.database.Cursor;

import java.util.List;

/**
 * Helper to the containers of the application
 */
public class ContainerHelper {

    /**
     * Checks if an array is null or empty
     *
     * @param array Array to check if it is empty
     * @param <T>   Type of the elements tha compose the array
     * @return False the array is not empty
     * True the array is null or empty
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null) || (array.length == 0);
    }

    /**
     * Checks if an array is not empty
     *
     * @param array Array to check
     * @param <T>   Type of the elements tha compose the array
     * @return False the array is null or empty
     * True the array is not empty
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a list is null or empty
     *
     * @param list List to check if it is empty
     * @param <T>  Type of the elements tha compose the list
     * @return False the list is not empty
     * True the list is null or empty
     */
    public static <T> boolean isEmpty(List<T> list) {
        return (list == null) || (list.isEmpty());
    }

    /**
     * Checks if a list is not empty
     *
     * @param list List to check
     * @param <T>  Type of the elements tha compose the list
     * @return False the list is null or empty
     * True the list is not empty
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * Checks if a cursor is null or empty
     *
     * @param cursor Cursor to check if it is empty
     * @return False the cursor is not empty
     * True the cursor is null or empty
     */
    public static boolean isEmpty(Cursor cursor) {
        return (cursor == null) || (cursor.getCount() == 0);
    }

    /**
     * Checks if a list is not empty
     *
     * @param cursor Cursor to check
     * @return False the cursor is null or empty
     * True the cursor is not empty
     */
    public static boolean isNotEmpty(Cursor cursor) {
        return !isEmpty(cursor);
    }
}
