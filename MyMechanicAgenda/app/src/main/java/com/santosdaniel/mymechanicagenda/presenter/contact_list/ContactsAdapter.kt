package com.santosdaniel.mymechanicagenda.presenter.contact_list

import android.app.Activity
import android.database.Cursor
import android.provider.ContactsContract.Contacts
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.presenter.GenericRViewCursorAdapter
import com.santosdaniel.mymechanicagenda.presenter.ListItemViewHolder
import com.santosdaniel.mymechanicagenda.view.GenericListItem

/**
 * Adapter of the list of elements
 */
class ContactsAdapter
/**
 * Constructor to the view adapter
 *
 * @param activity     Reference to the context where the adapter will be used
 * @param recyclerView Reference to the recycle view to use
 * @param progressBar  Reference to the progress bar that indicates to the user that is using data
 */
(activity: Activity, recyclerView: RecyclerView, progressBar: ProgressBar) : GenericRViewCursorAdapter(activity, recyclerView, progressBar) {

    /**
     * @param viewHolder reference to the view holder that support the item
     * @param cursor     cursor from where the data should be fetch
     * @return The GenericListItem associated with current cursor
     */
    override fun getGenericListItem(viewHolder: ListItemViewHolder, cursor: Cursor): GenericListItem {
        if (viewHolder.data == null) {
            viewHolder.data = GenericListItem()
        }
        val data = viewHolder.data

        //Read indexes
        val idIndex = cursor.getColumnIndex(Contacts._ID)
        val lookupKeyIndex = cursor.getColumnIndex(Contacts.LOOKUP_KEY)
        val displayNameIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY)
        val thumbnailUriIndex = cursor.getColumnIndex(Contacts.PHOTO_THUMBNAIL_URI)
        val photoUriIndex = cursor.getColumnIndex(Contacts.PHOTO_URI)

        //Read data
        val id = cursor.getLong(idIndex)
        val lookupKey = cursor.getString(lookupKeyIndex)
        val displayName = cursor.getString(displayNameIndex)
        val thumbnailUri = cursor.getString(thumbnailUriIndex)
        val photoUri = cursor.getString(photoUriIndex)

        if(data != null) {
            data.id = id
            data.lookUpKey = lookupKey
            data.title = displayName
            data.description = StringHelper.EMPTY_STRING
            data.imageUri = thumbnailUri
            data.imageUri = photoUri
        }

        return data!!
    }


    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        val item = getItemByView(v)
        if (item != null && !TextUtils.isEmpty(item.lookUpKey)) {
            //TODO:
            /*
            Intent intent = new Intent(activity, ReceiptDetailsActivity.class);
            intent.putExtra(ReceiptDetailsActivity.TITLE, item.title);
            intent.putExtra(ReceiptDetailsActivity.URL, item.detailsUrl);
            IntentHelper.startNewActivity(activity, v, intent);
            */
        }
    }

    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    override fun startLoading() = Unit

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    override fun stopLoading() = Unit

}
