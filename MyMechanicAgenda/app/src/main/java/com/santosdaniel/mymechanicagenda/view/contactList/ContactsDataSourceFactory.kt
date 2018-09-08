package com.santosdaniel.mymechanicagenda.view.contactList

import android.arch.paging.DataSource
import android.content.ContentResolver
import com.santosdaniel.mymechanicagenda.model.database.Contact

/**
 * Factory of data sources
 */
class ContactsDataSourceFactory(
        private val contentResolver: ContentResolver, private val contactDisplayName: String?
): DataSource.Factory<Int, Contact>() {

    override fun create(): DataSource<Int, Contact> {
        return ContactsDataSource(contentResolver, this.contactDisplayName)
    }
}