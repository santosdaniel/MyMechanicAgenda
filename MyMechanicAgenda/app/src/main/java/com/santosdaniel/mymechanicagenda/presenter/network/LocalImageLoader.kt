package com.santosdaniel.mymechanicagenda.presenter.network

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import com.android.volley.toolbox.ImageLoader.ImageCache

/**
 * Loads one image locally
 */
class LocalImageLoader
/**
 * Constructs a new ImageLoader.
 *
 * @param imageCache The cache to use as an L1 cache.
 */
(private val context: Context, private val imageCache: ImageCache) {

    /**
     *
     *
     * @param imageUri
     * @param thumbnail
     */
    fun load(imageUri: String, thumbnail: ImageView) {
        var bitmap: Bitmap? = imageCache.getBitmap(imageUri)
        if (bitmap == null) {
            try {
                val uri = Uri.parse(imageUri)
                bitmap = MediaStore.Images.Media.getBitmap(
                        context.contentResolver, uri)
                thumbnail.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.e(TAG, "Impossible to load image", e)
            }
        } else {
            //Was possible to get the image from the cache so is going to used it
            thumbnail.setImageBitmap(bitmap)
        }
    }

    companion object {

        /**
         * Maximum number of entries in the cache
         */
        private val TAG = "LocalImageLoader"
    }
}
