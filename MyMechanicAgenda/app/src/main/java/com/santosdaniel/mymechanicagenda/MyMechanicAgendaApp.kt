package com.santosdaniel.mymechanicagenda

import android.app.Application


/**
 * Application class responsible for keeping common logic
 */
class MyMechanicAgendaApp : Application() {

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     */
    override fun onCreate() {
        super.onCreate()
    }

    /**
     * Called when the application is going to terminate
     */
    override fun onTerminate() {
        super.onTerminate()
    }
}