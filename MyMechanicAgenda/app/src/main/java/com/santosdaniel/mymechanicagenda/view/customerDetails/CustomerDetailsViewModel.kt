package com.santosdaniel.mymechanicagenda.view.customerDetails

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import com.santosdaniel.mymechanicagenda.presenter.customerDetails.CustomerRepository


/**
 * View model used by the contact details activity
 */
class CustomerDetailsViewModel : ViewModel() {

    private var customer: LiveData<CustomerDetailsModel>? = null
    private val customerRepository: CustomerRepository = CustomerRepository()

    fun getCustomerDetailsModel(): LiveData<CustomerDetailsModel>? {
        return customer
    }

    fun init(lookupId: String) {

        /**
         * Only fetch data if is the first time doing it
         */
        if (this.customer == null) this.customer = this.customerRepository.loadByLookId(lookupId)
    }
}
