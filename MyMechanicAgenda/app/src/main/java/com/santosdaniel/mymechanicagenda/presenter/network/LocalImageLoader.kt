package com.santosdaniel.mymechanicagenda.presenter.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout

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
     * @param dp device independent pixel to use
     */
    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    private fun scaleImage(imageView: ImageView) {

        val drawing = imageView.drawable
        if (drawing == null) {
        }
        val bitmap = (drawing as BitmapDrawable).bitmap

        var width = bitmap.width
        var height = bitmap.height
        val bounding = dpToPx(imageView.width)

        val xScale = bounding.toFloat() / width
        val yScale = bounding.toFloat() / height
        val scale = if (xScale <= yScale) xScale else yScale
        val matrix = Matrix()
        matrix.postScale(scale, scale)

        val scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true)
        width = scaledBitmap.width // re-use
        height = scaledBitmap.height // re-use
        val result = BitmapDrawable(scaledBitmap)

        imageView.setImageDrawable(result)

        val params = imageView
                .layoutParams as LinearLayout.LayoutParams
        params.width = width
        params.height = height
        imageView.layoutParams = params

    }

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
                Log.d(TAG, bitmap!!.width.toString() + "")
                thumbnail.setImageBitmap(bitmap)
                scaleImage(thumbnail)
            } catch (e: Exception) {
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
