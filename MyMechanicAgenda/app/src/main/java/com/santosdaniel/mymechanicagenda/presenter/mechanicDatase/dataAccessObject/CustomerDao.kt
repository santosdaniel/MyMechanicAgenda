package com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.dataAccessObject

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.santosdaniel.mymechanicagenda.model.database.Customer


@Dao
interface CustomerDao : GenericDao<Customer> {

    @Query(value = "SELECT * FROM ${Customer.TABLE_NAME} WHERE ${Customer.LOOKUP_COLUMN_NAME} LIKE :lookup LIMIT 1")
    fun findByLookup(lookup: String): Customer


    @Query("DELETE from " + Customer.TABLE_NAME)
    fun deleteAll()
}