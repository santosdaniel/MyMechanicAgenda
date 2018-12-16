package com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.dataAccessObject

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface GenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customer: T): Long

    @Update
    fun update(customer: T): Int

    @Delete
    fun delete(customer: T): Int
}