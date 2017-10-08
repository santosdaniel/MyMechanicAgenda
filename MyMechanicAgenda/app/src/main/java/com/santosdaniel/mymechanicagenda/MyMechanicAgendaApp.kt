package com.santosdaniel.mymechanicagenda

import android.app.Application

import com.raizlabs.android.dbflow.config.FlowManager


/**
 * Application class responsible for keeping common logic
 */
class MyMechanicAgendaApp : Application() {

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     */
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }
}