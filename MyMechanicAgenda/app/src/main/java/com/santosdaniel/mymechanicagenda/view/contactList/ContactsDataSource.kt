package com.santosdaniel.mymechanicagenda.view.contactList

import android.arch.paging.PositionalDataSource
import android.content.ContentResolver
import android.provider.ContactsContract
import android.text.TextUtils
import com.santosdaniel.mymechanicagenda.model.database.Contact

class ContactsDataSource(private val contentResolver: ContentResolver, private val displayNameFilter: String?) :
        PositionalDataSource<Contact>() {

    companion object {
        private val PROJECTION = arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.LOOKUP_KEY,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
                ContactsContract.Contacts.PHOTO_URI
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Contact>) {
        callback.onResult(getContacts(params.requestedLoadSize, params.requestedStartPosition), 0)
    }

    /**
     * @return The string to select name (If the user is trying to search by any)
     */
    private fun getSelectionString(): String? =
            if (TextUtils.isEmpty(displayNameFilter)) {
                null
            } else {
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?"
            }

    /**
     * @param bundle Bundle passed to create the cursor
     * @return The string that should used while is making the like SQL
     */
    private fun getSelectionArgs(): Array<String>? =
            if (TextUtils.isEmpty(displayNameFilter)) {
                null
            } else {
                arrayOf("%$displayNameFilter%")
            }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Contact>) {
        val contacts = getContacts(params.loadSize, params.startPosition)
        callback.onResult(contacts)
    }

    private fun getContacts(limit: Int, offset: Int): MutableList<Contact> {
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                getSelectionString(),
                getSelectionArgs(),
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY +
                        " ASC LIMIT " + limit + " OFFSET " + offset)

        cursor.moveToFirst()
        val contacts: MutableList<Contact> = mutableListOf()

        //Read indexes
        val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
        val lookupKeyIndex = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
        val displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        val thumbnailUriIndex = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)
        val photoUriIndex = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)

        while (!cursor.isAfterLast) {
            //Read data
            val id = cursor.getLong(idIndex)
            val lookupKey = cursor.getString(lookupKeyIndex)
            val displayName = cursor.getString(displayNameIndex)
            val thumbnailUri = cursor.getString(thumbnailUriIndex)
            val photoUri = cursor.getString(photoUriIndex)


            contacts.add(Contact(id, lookupKey, displayName, thumbnailUri, photoUri))
            cursor.moveToNext()
        }
        cursor.close()

        return contacts
    }
}