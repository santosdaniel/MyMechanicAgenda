package com.santosdaniel.mymechanicagenda.presenter

import android.app.Activity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.generic.ViewHelper
import com.santosdaniel.mymechanicagenda.view.viewModel.GenericListItem

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 * that use a cursor to keep data
 */

abstract class GenericRViewCursorAdapter<EntityType>
/**
 * Constructor to the view adapter
 *
 * @param activity     Reference to the context where the adapter will be used
 * @param recyclerView Reference to the recycle view to use
 * @param progressBar  Reference to the progress bar that indicates to the user that is using data
 */
protected constructor(activity: Activity,
                      recyclerView: RecyclerView,
                      progressBar: ProgressBar,
                      diffCallback: DiffUtil.ItemCallback<EntityType>
) : GenericRecyclerViewAdapter<EntityType>(activity, recyclerView, progressBar, diffCallback) {

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
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder   The holder of the item of the recycle view
     * @param position The position of the the item
     */
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            val data = createGenericListItem(holder)
            fillItemData(data, item)

            //Load the image of the contact
            ViewHelper.loadImageOrDefault(activity, data.imageUri, R.mipmap.person, holder.thumbnail)
            //Loads the title of the item
            holder.title.text = data.title
            //TODO: If has a car show the model here
            holder.description.text = data.description
        }
    }


    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    override fun getItemByView(v: View): GenericListItem {
        val itemPosition = recyclerView.getChildLayoutPosition(v)
        val item = getItem(itemPosition)
        val data = GenericListItem()
        if (item != null)
            fillItemData(data, item)
        return data
    }


    /**
     *
     * @param data          reference to the item to fill the elements
     * @param itemData        cursor from where the data should be fetch
     *
     * @return The GenericListItem associated with current cursor
     */
    abstract fun fillItemData(data: GenericListItem, itemData: EntityType)
}
