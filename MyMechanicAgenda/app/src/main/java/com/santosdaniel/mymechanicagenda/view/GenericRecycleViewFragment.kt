package com.santosdaniel.mymechanicagenda.view

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar


/**
 * Show be extended by all the fragment made to provide a recyclerView to the user
 */
abstract class GenericRecycleViewFragment<T : RecyclerView.Adapter<*>> : Fragment() {

    /**
     * Reference to the recycle view where the items will be show
     */
    protected var lstResults: RecyclerView? = null

    /**
     * The adapter of the recycle view
     */
    protected var lstAdapter: T? = null

    /**
     * The layout manager of the recycleView
     */
    protected var lstLayoutManager: RecyclerView.LayoutManager? = null

    /**
     * Used to indicate that is trying to load the data to the recycleView
     */
    protected var loadProgress: ProgressBar? = null
}
