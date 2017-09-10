package com.santosdaniel.mymechanicagenda.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;


/**
 * Show be extended by all the fragment made to provide a recyclerView to the user
 */
public abstract class GenericRecycleViewFragment<T extends RecyclerView.Adapter> extends Fragment {

    /**
     * Reference to the recycle view where the items will be show
     */
    protected RecyclerView lstResults;

    /**
     * The adapter of the recycle view
     */
    protected T lstAdapter;

    /**
     * The layout manager of the recycleView
     */
    protected RecyclerView.LayoutManager lstLayoutManager;

    /**
     * Used to indicate that is trying to load the data to the recycleView
     */
    protected ProgressBar loadProgress;
}
