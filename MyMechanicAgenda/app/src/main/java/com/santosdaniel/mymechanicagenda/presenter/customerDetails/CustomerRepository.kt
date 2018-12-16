package com.santosdaniel.mymechanicagenda.presenter.customerDetails


import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import com.santosdaniel.mymechanicagenda.helper.LogHelper
import com.santosdaniel.mymechanicagenda.model.database.Customer
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.GenericRepository
import com.santosdaniel.mymechanicagenda.view.customerDetails.CustomerDetailsModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context


/**
 * Has the methods to use the contact model
 */
/**
 * The constructor of the repository
 */
class CustomerRepository(context: Context) : GenericRepository<Customer>(context) {

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
    override fun update(customer: Customer): Int {
        return try {
            daoProvider.customerDao.update(customer)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            0
        }

    }

    /**
     * @property lookupId   Lookup identifier of the contact
     * @property callback   The method that is going to be call back when the query is finished
     *
     * @return The costumer with that lookup identifier
     */
    fun loadByLookId(lookupId: String, @NonNull callback: QueryTransaction.QueryResultSingleCallback<Customer>) {
        try {
            daoProvider.customerDao.findByLookup(lookupId)

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

    fun loadByLookId(lookupId: String): LiveData<CustomerDetailsModel> {
        val data = MutableLiveData<CustomerDetailsModel>()
        var customerDetailsModel = CustomerDetailsModel()
        customerDetailsModel.lookupId = lookupId
        customerDetailsModel.title = "Test"
        customerDetailsModel.imageUri = null

        data.value = customerDetailsModel
        return data
    }

    companion object {

        private const val TAG = "CustomerRepository"
    }
}
