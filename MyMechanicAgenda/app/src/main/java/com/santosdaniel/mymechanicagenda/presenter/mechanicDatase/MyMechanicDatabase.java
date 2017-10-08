package com.santosdaniel.mymechanicagenda.presenter;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Create the database
 */
@Database(name = MyMechanicDatabase.NAME, version = MyMechanicDatabase.VERSION)
public class MyMechanicDatabase {
    public static final String NAME = "MyMechanicDatabase";

    public static final int VERSION = 1;
}
