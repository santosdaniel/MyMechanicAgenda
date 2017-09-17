package com.santosdaniel.mymechanicagenda.presenter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericListItem

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 */
abstract class GenericRecyclerViewAdapter
/**
 * Constructor to the view adapter
 *
 * @param activity     Reference to the context where the adapter will be used
 * @param recyclerView Reference to the recycle view to use
 * @param progressBar  Reference to the progress bar that indicates to the user that is using data
 */
protected constructor(protected val activity: Activity,
                      protected val recyclerView: RecyclerView,
                      private val progressBar: View
) : RecyclerView.Adapter<ListItemViewHolder>(), View.OnClickListener {


    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent   The view group of the item
     * @param viewType The type of view
     * @return The item view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListItemViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.generic_list_item, parent, false)
        // set the view's size, margins, padding and layout parameters
        v.setOnClickListener(this)
        return ListItemViewHolder(v)
    }


    /**
     * Indicates that the recycle view is loading its content
     * (The adapter is going to show or hide the the views in order to give feedback to the user
     *
     * @param isLoading False that indicates if it is loading the content or not
     */
    fun setIsLoading(isLoading: Boolean) {
        if (isLoading) {
            this.progressBar.visibility = View.VISIBLE
            this.recyclerView.visibility = View.INVISIBLE
        } else {
            this.progressBar.visibility = View.GONE
            this.recyclerView.visibility = View.VISIBLE
        }
    }

    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    protected abstract fun getItemByView(v: View): GenericListItem


    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    abstract override fun onClick(v: View)


    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    abstract fun startLoading()

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    abstract fun stopLoading()

    companion object {

        protected val NO_ELEMENTS = 0
    }

}
