package com.santosdaniel.mymechanicagenda.presenter;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper;
import com.santosdaniel.mymechanicagenda.helper.UIHelper;
import com.santosdaniel.mymechanicagenda.presenter.network.LocalImageLoader;
import com.santosdaniel.mymechanicagenda.presenter.network.NetworkRequestsSingleton;
import com.santosdaniel.mymechanicagenda.view.GenericListItem;

/**
 *  Adapter with generic stuff that should be common to all the recycle view adapters
 *  that use a cursor to keep data
 */

public abstract class GenericRViewCursorAdapter extends GenericRecyclerViewAdapter {

    /**
     * Cursor with data for the recycle view
     */
    private Cursor cursor;

    /**
     * Constructor to the view adapter
     *
     * @param activity     Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    protected GenericRViewCursorAdapter(@NonNull Activity activity, @NonNull RecyclerView recyclerView, @NonNull ProgressBar progressBar) {
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
        if (ContainerHelper.INSTANCE.isNotEmpty(cursor) && (position < cursor.getCount())) {
            cursor.moveToPosition(position);

            GenericListItem item = getGenericListItem(holder, cursor);

            if (TextUtils.isEmpty(item.getImageUri())) {
                //Reset the content of the thumbnail
                Drawable unknown = UIHelper.INSTANCE.getDrawable(getActivity(), R.mipmap.person);
                holder.getThumbnail().setImageDrawable(unknown);
            } else {
                LocalImageLoader imageLoader = NetworkRequestsSingleton.Companion.getInstance(getActivity().getApplicationContext()).getLocalImageLoader();
                imageLoader.load(item.getImageUri(), holder.getThumbnail());
            }
            holder.getTitle().setText(item.getTitle());
            //TODO: If has a car show the model here
            holder.getDescription().setText(item.getDescription());
        }
    }

    /**
     * @return The number of items that have
     */
    @Override
    public int getItemCount() {
        return ContainerHelper.INSTANCE.isEmpty(cursor) ? Companion.getNO_ELEMENTS() : cursor.getCount();
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
        cursor.moveToPosition(itemPosition);
        //TODO:
        return null;
    }

    /**
     * @param cursor The reference to the new cursor
     */
    public void setDataSet(Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     *
     * @param viewHolder    reference to the view holder that support the item
     * @param cursor        cursor from where the data should be fetch
     *
     * @return The GenericListItem associated with current cursor
     */
    public abstract GenericListItem getGenericListItem(ListItemViewHolder viewHolder, Cursor cursor);
}
