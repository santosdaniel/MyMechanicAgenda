package com.santosdaniel.mymechanicagenda.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View

import java.lang.ref.WeakReference


/**
 * Helper to access to the elements to put in the views
 */
object UIHelper {


    /**
     * @param context    Context that is calling this method
     * @param drawableId The identifier of the drawable to get
     * @return Drawable that has the identifier passed (If possible)
     */
    fun getDrawable(context: Context, drawableId: Int): Drawable? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.getDrawable(drawableId)
            } else {
                context.resources.getDrawable(drawableId)
            }

    /**
     * Set the visibility parameter of a set of views
     *
     * @param visibility The visibility to set
     * @param views      The array of views to set the visibility
     */
    fun setVisibility(visibility: Int, vararg views: View) {
        if (ContainerHelper.isNotEmpty(views)) {
            for (view in views) {
                view.visibility = visibility
            }
        }
    }

    /**
     * Set the visibility parameter of a set of weak references to views
     *
     * @param visibility The visibility to set
     * @param refViews   The array of views to set the visibility
     */
    fun setVisibility(visibility: Int, vararg refViews: WeakReference<View>) {
        if (ContainerHelper.isNotEmpty(refViews)) {
            for (refView in refViews) {
                try {
                    val view = refView.get()
                    if (view != null) {
                        view.visibility = visibility
                    }
                } catch (e: Exception) {

                }

            }
        }
    }
}
