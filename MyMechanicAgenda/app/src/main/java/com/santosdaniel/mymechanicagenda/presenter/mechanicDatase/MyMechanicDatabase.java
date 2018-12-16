package com.santosdaniel.mymechanicagenda.presenter.mechanicDatase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.santosdaniel.mymechanicagenda.model.database.BrandEnumConverter;
import com.santosdaniel.mymechanicagenda.model.database.CostumerVehicle;
import com.santosdaniel.mymechanicagenda.model.database.Customer;
import com.santosdaniel.mymechanicagenda.model.database.DateConverter;
import com.santosdaniel.mymechanicagenda.model.database.Document;
import com.santosdaniel.mymechanicagenda.model.database.DocumentPhoto;
import com.santosdaniel.mymechanicagenda.model.database.DocumentTypeConverter;
import com.santosdaniel.mymechanicagenda.model.database.Reparation;
import com.santosdaniel.mymechanicagenda.model.database.TimeUnitConverter;
import com.santosdaniel.mymechanicagenda.model.database.Vehicle;
import com.santosdaniel.mymechanicagenda.model.database.VehicleModel;
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.dataAccessObject.CustomerDao;

/**
 * Definition of the database
 */

@Database(entities = {
        CostumerVehicle.class,
        Customer.class,
        Vehicle.class,
        Document.class,
        DocumentPhoto.class,
        Reparation.class,
        VehicleModel.class,
}, version = 1, exportSchema = false)
@TypeConverters({
        DateConverter.class,
        BrandEnumConverter.class,
        DocumentTypeConverter.class,
        TimeUnitConverter.class
})
public abstract class MyMechanicDatabase extends RoomDatabase {
    @SuppressWarnings("WeakerAccess")
    public static final String DB_NAME = "myMechanicDatabase.db";

    @SuppressWarnings("WeakerAccess")
    public static final int VERSION = 1;

    private static volatile MyMechanicDatabase instance;

    private static MyMechanicDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                MyMechanicDatabase.class,
                DB_NAME).build();
    }

    public static synchronized MyMechanicDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }



    public abstract CustomerDao getCustomerDao();
}
