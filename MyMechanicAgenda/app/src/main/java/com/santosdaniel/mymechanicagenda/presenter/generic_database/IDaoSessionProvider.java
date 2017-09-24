package com.santosdaniel.mymechanicagenda.presenter.generic_database;

import com.santosdaniel.mymechanicagenda.model.database.DaoSession;

/**
 * Provides the dao
 */

public interface IDaoSessionProvider {

    /**
     * @return  The data access object session
     */
    DaoSession getDaoSession();
}
