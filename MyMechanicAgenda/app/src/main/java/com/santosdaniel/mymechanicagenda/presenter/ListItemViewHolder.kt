package com.santosdaniel.mymechanicagenda.presenter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.viewModel.GenericListItem

/**
 * View holder to the recycle view
 */
class ListItemViewHolder
/**
 * In the constructor of the view holder also find the
 * sub-elements of the item
 *
 * @param v View that is going to support the item of the list
 */
(v: View) : RecyclerView.ViewHolder(v) {

    /**
     * Thumbnail of the item in the list
     */
    val thumbnail: ImageView = v.findViewById(R.id.thumbnail)

    /**
     * Title of the item
     */
    val title: TextView = v.findViewById(R.id.title)

    /**
     * Description of the item
     */
    val description: TextView = v.findViewById(R.id.description)

    /**
     * Has the reference to the data supported (Like that reduces the number of objects created)
     */
    var data: GenericListItem? = null

}
