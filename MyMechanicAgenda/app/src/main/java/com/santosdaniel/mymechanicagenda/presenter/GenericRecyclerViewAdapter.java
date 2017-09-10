package com.santosdaniel.mymechanicagenda.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.view.GenericListItem;

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 */
public abstract class GenericRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> implements View.OnClickListener {

    protected final Activity activity;
    protected final RecyclerView recyclerView;
    private final View progressBar;

    protected static final int NO_ELEMENTS = 0;

    /**
     * Constructor to the view adapter
     *
     * @param activity     Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    protected GenericRecyclerViewAdapter(@NonNull Activity activity,
                                         @NonNull RecyclerView recyclerView,
                                         @NonNull View progressBar
    ) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
    }


    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent   The view group of the item
     * @param viewType The type of view
     * @return The item view holder
     */
    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generic_list_item, parent, false);
        // set the view's size, margins, padding and layout parameters
        v.setOnClickListener(this);
        return new ListItemViewHolder(v);
    }


    /**
     * Indicates that the recycle view is loading its content
     * (The adapter is going to show or hide the the views in order to give feedback to the user
     *
     * @param isLoading False that indicates if it is loading the content or not
     */
    public void setIsLoading(boolean isLoading) {
        if (isLoading) {
            this.progressBar.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
        } else {
            this.progressBar.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    protected abstract GenericListItem getItemByView(View v);


    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    @Override
    public abstract void onClick(View v);


    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    @SuppressWarnings("unused")
    public abstract void startLoading();

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    @SuppressWarnings({"unused", "EmptyMethod"})
    public abstract void stopLoading();

}
