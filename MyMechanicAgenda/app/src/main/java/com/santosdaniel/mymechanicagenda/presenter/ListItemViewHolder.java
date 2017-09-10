package com.santosdaniel.mymechanicagenda.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.view.GenericListItem;

/**
 * View holder to the recycle view
 */
public class ListItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * Thumbnail of the item in the list
     */
    public final ImageView thumbnail;

    /**
     * Title of the item
     */
    public final TextView title;

    /**
     * Description of the item
     */
    public final TextView description;

    /**
     * Has the reference to the data supported (Like that reduces the number of objects created)
     */
    public GenericListItem data;

    /**
     * In the constructor of the view holder also find the
     * sub-elements of the item
     *
     * @param v View that is going to support the item of the list
     */
    public ListItemViewHolder(@NonNull View v) {
        super(v);
        this.thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        this.title = (TextView) v.findViewById(R.id.title);
        this.description = (TextView) v.findViewById(R.id.description);
    }
}
