package com.santosdaniel.mymechanicagenda.presenter

import android.app.Activity
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.helper.UIHelper
import com.santosdaniel.mymechanicagenda.presenter.network.NetworkRequestsSingleton
import com.santosdaniel.mymechanicagenda.view.GenericListItem

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 * that use a cursor to keep data
 */

abstract class GenericRViewCursorAdapter
/**
 * Constructor to the view adapter
 *
 * @param activity     Reference to the context where the adapter will be used
 * @param recyclerView Reference to the recycle view to use
 * @param progressBar  Reference to the progress bar that indicates to the user that is using data
 */
protected constructor(activity: Activity, recyclerView: RecyclerView, progressBar: ProgressBar) : GenericRecyclerViewAdapter(activity, recyclerView, progressBar) {

    /**
     * Creates a new reference to a generic item or reuses the old one
     *
     * @param viewHolder holder of the view that has the item
     */
    private fun createGenericListItem(viewHolder: ListItemViewHolder): GenericListItem {
        if (viewHolder.data == null) {
            viewHolder.data = GenericListItem()
        }
        return viewHolder.data!!
    }

    /**
     * Cursor with data for the recycle view
     */
    private var cursor: Cursor? = null

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder   The holder of the item of the recycle view
     * @param position The position of the the item
     */
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        if (ContainerHelper.isNotEmpty(cursor)) {
            val pCursor = cursor!!
            if (position < pCursor.count) {

                pCursor.moveToPosition(position)

                val data = createGenericListItem(holder)
                fillItemData(data, pCursor)

                if (TextUtils.isEmpty(data.imageUri)) {
                    //Reset the content of the thumbnail
                    val unknown = UIHelper.getDrawable(activity, R.mipmap.person)
                    holder.thumbnail.setImageDrawable(unknown)
                } else {
                    val imageLoader = NetworkRequestsSingleton.getInstance(activity.applicationContext).localImageLoader
                    imageLoader.load(data.imageUri!!, holder.thumbnail)
                }
                holder.title.text = data.title
                //TODO: If has a car show the model here
                holder.description.text = data.description
            }
        }
    }

    /**
     * @return The number of items that have
     */
    override fun getItemCount(): Int =
            if (ContainerHelper.isEmpty(cursor)) GenericRecyclerViewAdapter.Companion.NO_ELEMENTS else cursor!!.count

    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    override fun getItemByView(v: View): GenericListItem {
        val itemPosition = recyclerView.getChildLayoutPosition(v)
        cursor!!.moveToPosition(itemPosition)
        val data = GenericListItem()
        fillItemData(data, cursor!!)
        return data
    }

    /**
     * @param cursor The reference to the new cursor
     */
    fun setDataSet(cursor: Cursor?) {
        this.cursor = cursor
    }

    /**
     *
     * @param data          reference to the item to fill the elements
     * @param cursor        cursor from where the data should be fetch
     *
     * @return The GenericListItem associated with current cursor
     */
    abstract fun fillItemData(data: GenericListItem, cursor: Cursor)
}
