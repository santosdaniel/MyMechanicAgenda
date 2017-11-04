package com.santosdaniel.mymechanicagenda.presenter.customerDetails


import android.support.annotation.NonNull
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction
import com.santosdaniel.mymechanicagenda.helper.LogHelper
import com.santosdaniel.mymechanicagenda.model.database.Customer
import com.santosdaniel.mymechanicagenda.model.database.Customer_Table
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.GenericRepository


/**
 * Has the methods to use the contact model
 */
/**
 * The constructor of the repository
 */
class CustomerRepository : GenericRepository<Customer>() {

    /**
     * Create a new customer
     *
     * @param customer the customer that is to create
     */
    override fun create(customer: Customer): Long? {
        return try {
            daoProvider.customerDao.insert(customer)
        } catch (e: Throwable) {
            LogHelper.e(TAG, e.message)
            null
        }

    }

    /**
     * Update an existing entity
     *
     * @param customer the entity that to update
     */
    override fun update(customer: Customer): Boolean {
        return try {
            daoProvider.customerDao.update(customer)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            false
        }

    }

    /**
     * @property lookupId   Lookup identifier of the contact
     * @property callback   The method that is going to be call back when the query is finished
     *
     * @return The costumer with that lookup identifier
     */
    fun loadByLookId(lookupId: String, @NonNull callback : QueryTransaction.QueryResultSingleCallback<Customer>) {
        try {
            SQLite.select()
                    .from(Customer::class)
                    .where(Customer_Table.lookup.eq(lookupId))
                    .async()
                    .querySingleResultCallback(callback)
                    .execute()
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
        }
    }

    companion object {

        private val TAG = "CustomerRepository"
    }
}
