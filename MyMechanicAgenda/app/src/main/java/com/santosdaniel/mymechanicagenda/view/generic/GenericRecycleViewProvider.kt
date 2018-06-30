package com.santosdaniel.mymechanicagenda.view.generic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

/**
 * provide a set of properties used by the views where recycle views are used
 */
class GenericRecycleViewProvider<T : RecyclerView.Adapter<*>> {

    /**
     * Reference to the recycle view where the items will be show
     */
    var lstResults: RecyclerView? = null

    /**
     * Used to indicate that is trying to load the data to the recycleView
     */
    var loadProgress: ProgressBar? = null

    /**
     * The adapter of the recycle view
     */
    var lstAdapter: T? = null

    /**
     * The layout manager of the recycleView
     */
    var lstLayoutManager: RecyclerView.LayoutManager? = null


    /**
     * Bind the views of the recycle view to the local variables
     */
    fun bindViews(view: View, itemsListId: Int, loadProgressId: Int) {
        this.lstResults = view.findViewById(itemsListId)
        this.loadProgress = view.findViewById(loadProgressId)


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        lstResults!!.setHasFixedSize(false)
    }

    fun setLayoutManager(linearLayoutManager: RecyclerView.LayoutManager) {
        if(lstResults != null) {
            lstResults!!.layoutManager = linearLayoutManager
        }
    }
}
