package com.santosdaniel.mymechanicagenda.helper

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import com.santosdaniel.mymechanicagenda.presenter.network.NetworkRequestsSingleton

/**
 * Helper with methods to the views
 */
object ViewHelper {

    /**
     * Show a certain snack bar
     */
    fun showSnackBar(view: View) =
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()


    private fun pLoadImageOrDefault(activity: Activity, imageUri: String?, defaultDrawableId: Int, imageView: ImageView) {
        if (StringHelper.isNullOrEmpty(imageUri)) {
            //Reset the content of the thumbnail
            val defaultIcon = UIHelper.getDrawable(activity, defaultDrawableId)
            imageView.setImageDrawable(defaultIcon)
        } else {
            //Uses the loader of images to make to load asynchronous
            val imageLoader = NetworkRequestsSingleton.getInstance(activity.applicationContext).localImageLoader
            imageLoader.load(imageUri!!, imageView)
        }
    }

    /**
     *  Loads an image or end ups loading the default image passed
     *
     * @property activity reference to the activity where the image is going to be load
     * @property imageUri uri to the image to load
     * @property defaultDrawableId identifier of the drawable used if the image uri does not exists
     * @property imageView imageView where is to load the image
     */
    fun loadImageOrDefault(activity: Activity, imageUri: String?, defaultDrawableId: Int, imageView: ImageView?) {
        if (imageView != null) {
            pLoadImageOrDefault(activity, imageUri, defaultDrawableId, imageView)
        }
    }
}
