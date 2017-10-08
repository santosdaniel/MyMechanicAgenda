package com.santosdaniel.mymechanicagenda.helper

import android.util.Log

/**
 * Helper to log information about the application
 */

object LogHelper {

    /**
     * Logs an error of the application
     *
     * @param tag     The tag to use while logging the the error
     * @param message The message to get logged
     */
    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }


    /**
     * Logs an error of the application
     *
     * @param tag       The tag to use while logging the the error
     * @param message   The message to get logged
     * @param throwable The throwable
     */
    fun e(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }
}
