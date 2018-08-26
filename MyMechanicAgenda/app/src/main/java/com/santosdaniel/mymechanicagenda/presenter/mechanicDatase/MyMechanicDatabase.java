package com.santosdaniel.mymechanicagenda.presenter.mechanicDatase;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Definition of the database
 */
@Database(name = MyMechanicDatabase.NAME, version = MyMechanicDatabase.VERSION)
public class MyMechanicDatabase {
    @SuppressWarnings("WeakerAccess")
    public static final String NAME = "MyMechanicDatabase";

    @SuppressWarnings("WeakerAccess")
    public static final int VERSION = 2;
}
