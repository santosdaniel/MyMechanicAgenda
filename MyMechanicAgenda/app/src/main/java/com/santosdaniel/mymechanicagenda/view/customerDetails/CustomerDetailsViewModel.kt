package com.santosdaniel.mymechanicagenda.view.customerDetails

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData






/**
 * View model used by the contact details activity
 */
class CustomerDetailsViewModel : ViewModel() {

    private val user: LiveData<CustomerDetailsModel>? = null

    fun getCustomerDetailsModel(): LiveData<CustomerDetailsModel>? {
        return user
    }
}
