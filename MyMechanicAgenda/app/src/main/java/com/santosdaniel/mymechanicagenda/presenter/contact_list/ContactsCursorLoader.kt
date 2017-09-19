package com.santosdaniel.mymechanicagenda.presenter.contact_list

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.support.v4.content.CursorLoader
import android.text.TextUtils

/**
 * Loader for contacts of the user
 */
class ContactsCursorLoader
/**
 * Creates an empty unspecified CursorLoader.
 *
 * @param context Reference to the context where the data is going to be loaded
 */
(context: Context, bundle: Bundle) : CursorLoader(context,
        Contacts.CONTENT_URI,
        PROJECTION, getSelectionString(bundle),
        getSelectionArgs(bundle),
        SORT_ORDER
) {
    companion object {
        //Key of the name (when passed in the bundle
        val NAME_ATTR = "name"


        //Define a constant that contains the columns you want to return from your query
        private val PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Phone._ID,
                Contacts.LOOKUP_KEY,
                Contacts.DISPLAY_NAME_PRIMARY,
                Contacts.PHOTO_THUMBNAIL_URI,
                Contacts.PHOTO_URI)

        /**
         * @param bundle Bundle passed to create the cursor
         * @return The string to select name (If the user is trying to search by any)
         */
        private fun getSelectionString(bundle: Bundle?): String? =
                if (bundle == null || TextUtils.isEmpty(bundle.getString(NAME_ATTR))) {
                    null
                } else {
                    Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?"
                }

        /**
         * @param bundle Bundle passed to create the cursor
         * @return The string that should used while is making the like SQL
         */
        private fun getSelectionArgs(bundle: Bundle?): Array<String>? =
                if (bundle == null || TextUtils.isEmpty(bundle.getString(NAME_ATTR))) {
                    null
                } else {
                    arrayOf("%" + bundle.getString(NAME_ATTR) + "%")
                }

        /**
         * Makes the contacts to be sorted by name
         */
        private val SORT_ORDER = Contacts.DISPLAY_NAME_PRIMARY + " ASC"
    }
}
