package com.santosdaniel.mymechanicagenda.presenter.network

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.ImageView
import com.android.volley.toolbox.ImageLoader.ImageCache
import com.santosdaniel.mymechanicagenda.helper.TakePictureHelper
import java.io.File
import java.io.FileNotFoundException

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


    private fun load(path: String, uri: Uri, thumbnail: ImageView) {
        val bitmap: Bitmap? = MediaStore.Images.Media.getBitmap(
                context.contentResolver, uri)
        thumbnail.setImageBitmap(bitmap)
        if (bitmap != null) {
            imageCache.putBitmap(path, bitmap)
        }
    }

    /**
     *
     *
     * @param imageUri
     * @param thumbnail
     */
    fun load(imageUri: String, thumbnail: ImageView): Boolean {
        val bitmap: Bitmap? = imageCache.getBitmap(imageUri)
        if (bitmap == null) {
            try {
                val uri = Uri.parse(imageUri)
                load(imageUri, uri, thumbnail)
                return true
            } catch (fNotFound: FileNotFoundException) {
                try {
                    val photoFile = File(imageUri)
                    val photoURI = FileProvider.getUriForFile(context,
                            TakePictureHelper.FILE_PROVIDER_AUTHORITY,
                            photoFile)
                    load(imageUri, photoURI, thumbnail)
                    return true
                } catch (e: Exception) {
                    Log.e(TAG, "Impossible to load image", e)
                    return false
                }
            }
        } else {
            //Was possible to get the image from the cache so is going to used it
            thumbnail.setImageBitmap(bitmap)
            return true
        }
    }

    companion object {

        /**
         * Maximum number of entries in the cache
         */
        private const val TAG = "LocalImageLoader"
    }
}
