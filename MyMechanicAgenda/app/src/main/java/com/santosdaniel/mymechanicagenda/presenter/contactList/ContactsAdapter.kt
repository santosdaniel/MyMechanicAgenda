package com.santosdaniel.mymechanicagenda.presenter.contactList

import android.app.Activity
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.santosdaniel.mymechanicagenda.helper.IntentHelper
import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.model.database.Contact
import com.santosdaniel.mymechanicagenda.presenter.GenericRViewCursorAdapter
import com.santosdaniel.mymechanicagenda.view.customerDetails.CustomerDetailsActivity
import com.santosdaniel.mymechanicagenda.view.viewModel.GenericListItem

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
(activity: Activity, recyclerView: RecyclerView, progressBar: ProgressBar) :
        GenericRViewCursorAdapter<Contact>(activity, recyclerView, progressBar, ContactsDiff) {

    /**
     *
     * @param data          reference to the item to fill the elements
     * @param contact       Reference to the contact to be load
     *
     * @return The GenericListItem associated with current cursor
     */
    override fun fillItemData(data: GenericListItem, contact: Contact) {
        //Set the data
        data.id = contact.id
        data.lookUpKey = contact.lookupKey
        data.title = contact.displayName
        data.description = StringHelper.EMPTY_STRING
        data.thumbnailUri = contact.thumbnailUri
        data.imageUri = contact.photoUri
    }


    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        val item = getItemByView(v)
        if (item != null && StringHelper.isNotNullOrEmpty(item.lookUpKey)) {
            val intent = Intent(activity, CustomerDetailsActivity::class.java)
            intent.putExtra(CustomerDetailsActivity.LOOKUP_KEY, item.lookUpKey)
            intent.putExtra(CustomerDetailsActivity.TITLE_KEY, item.title)
            intent.putExtra(CustomerDetailsActivity.IMAGE_URI_KEY, item.imageUri)
            IntentHelper.startNewActivity(activity, v, intent)

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


    companion object {
        private val ContactsDiff = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
