package com.santosdaniel.mymechanicagenda.view.contactList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.database.Cursor

class ContactListViewModel: ViewModel() {
    private var customer: LiveData<Cursor>? = null

    fun init() {
//
    }
}
