package com.santosdaniel.mymechanicagenda;

import android.app.Application;

import com.santosdaniel.mymechanicagenda.model.database.DaoMaster;
import com.santosdaniel.mymechanicagenda.model.database.DaoSession;
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.IDaoSessionProvider;

import org.greenrobot.greendao.database.Database;

/**
 * Application class responsible for keeping common logic
 */
public class MyMechanicAgendaApp extends Application implements IDaoSessionProvider {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}