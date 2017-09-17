package com.santosdaniel.mymechanicagenda.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.toolbox.ImageLoader;
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper;
import com.santosdaniel.mymechanicagenda.presenter.network.NetworkRequestsSingleton;
import com.santosdaniel.mymechanicagenda.view.GenericListItem;

import java.util.List;

/**
 *  Adapter with generic stuff that should be common to all the recycle view adapters
 *  that use list to save their data
 */

public abstract class GenericRViewListAdapter extends GenericRecyclerViewAdapter {

    /**
     * Data of the Recycle View adapter
     */
    private List<GenericListItem> dataSet;

    /**
     * Constructor to the view adapter
     *
     * @param activity     Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    protected GenericRViewListAdapter(@NonNull Activity activity, @NonNull RecyclerView recyclerView, @NonNull ProgressBar progressBar) {
        super(activity, recyclerView, progressBar);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder   The holder of the item of the recycle view
     * @param position The position of the the item
     */
    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        if (ContainerHelper.INSTANCE.isNotEmpty(dataSet) && (position < dataSet.size())) {
            GenericListItem item = dataSet.get(position);

            if (TextUtils.isEmpty(item.getImageUri())) {
                holder.getThumbnail().setImageBitmap(null);
            } else {
                ImageLoader imageLoader = NetworkRequestsSingleton.Companion.getInstance(getActivity().getApplicationContext()).getNetworkImageLoader();
            }
            holder.getTitle().setText(item.getTitle());
            holder.getDescription().setText(item.getDescription());
        }
    }

    /**
     * @return The number of items that have
     */
    @Override
    public int getItemCount() {
        return ContainerHelper.INSTANCE.isEmpty(dataSet) ? Companion.getNO_ELEMENTS() : dataSet.size();
    }

    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    @Override
    protected GenericListItem getItemByView(View v) {
        int itemPosition = getRecyclerView().getChildLayoutPosition(v);
        return dataSet.get(itemPosition);
    }

    /**
     * @param dataSet The reference to the new data set
     */
    public void setDataSet(List<GenericListItem> dataSet) {
        this.dataSet = dataSet;
    }
}
