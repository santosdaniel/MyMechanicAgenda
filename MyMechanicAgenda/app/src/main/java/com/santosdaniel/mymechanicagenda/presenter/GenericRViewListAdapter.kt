package com.santosdaniel.mymechanicagenda.presenter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar

import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.presenter.network.NetworkRequestsSingleton
import com.santosdaniel.mymechanicagenda.view.GenericListItem

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 * that use list to save their data
 */

internal abstract class GenericRViewListAdapter
/**
 * Constructor to the view adapter
 *
 * @param activity     Reference to the context where the adapter will be used
 * @param recyclerView Reference to the recycle view to use
 * @param progressBar  Reference to the progress bar that indicates to the user that is using data
 */
protected constructor(activity: Activity, recyclerView: RecyclerView, progressBar: ProgressBar) : GenericRecyclerViewAdapter(activity, recyclerView, progressBar) {

    /**
     * Data of the Recycle View adapter
     */
    private var dataSet: List<GenericListItem>? = null

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder   The holder of the item of the recycle view
     * @param position The position of the the item
     */
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        if (ContainerHelper.isNotEmpty(dataSet!!) && position < dataSet!!.size) {
            val (_, _, imageUri, _, title, description) = dataSet!![position]

            if (TextUtils.isEmpty(imageUri)) {
                holder.thumbnail.setImageBitmap(null)
            } else {
                val imageLoader = NetworkRequestsSingleton.getInstance(activity.applicationContext).networkImageLoader
            }
            holder.title.text = title
            holder.description.text = description
        }
    }

    /**
     * @return The number of items that have
     */
    override fun getItemCount(): Int =
            if (ContainerHelper.isEmpty(dataSet)) GenericRecyclerViewAdapter.NO_ELEMENTS else dataSet!!.size

    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    override fun getItemByView(v: View): GenericListItem {
        val itemPosition = recyclerView.getChildLayoutPosition(v)
        return dataSet!![itemPosition]
    }

    /**
     * @param dataSet The reference to the new data set
     */
    fun setDataSet(dataSet: List<GenericListItem>) {
        this.dataSet = dataSet
    }
}
