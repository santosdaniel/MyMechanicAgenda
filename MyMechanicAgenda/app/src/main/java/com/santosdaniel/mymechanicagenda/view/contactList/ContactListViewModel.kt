package com.santosdaniel.mymechanicagenda.view.contactList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.ContentResolver
import com.santosdaniel.mymechanicagenda.model.database.Contact

class ContactListViewModel: ViewModel() {
    lateinit var contactsList: LiveData<PagedList<Contact>>

    fun loadContacts(contentResolver: ContentResolver, displayNameFilter: String?) {
        val contactsDataSourceFactory = ContactsDataSourceFactory(contentResolver, displayNameFilter)

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()

        contactsList = LivePagedListBuilder<Int, Contact>(contactsDataSourceFactory, config).build()
    }
}
