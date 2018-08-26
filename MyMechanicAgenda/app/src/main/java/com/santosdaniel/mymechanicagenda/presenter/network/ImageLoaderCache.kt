package com.santosdaniel.mymechanicagenda.presenter.network

import android.graphics.Bitmap
import android.support.v4.util.LruCache

import com.android.volley.toolbox.ImageLoader

/**
 * Cache that is going to keep the images response of images from network
 * in cache
 */
internal class ImageLoaderCache : ImageLoader.ImageCache {

    private val cache: LruCache<String, Bitmap>

    /**
     * Constructor of the image cache loader
     */
    init {
        this.cache = LruCache(MAX_NUMBER_OF_ENTRIES)
    }

    /**
     * Fetch one bitmap from cache
     *
     * @param url The url where the image was loaded
     * @return The bitmap that exists in cache
     */
    override fun getBitmap(url: String): Bitmap? = cache.get(url)

    /**
     * Put one bitmap in cache
     *
     * @param url    The url where the image was loaded
     * @param bitmap The bitmap that is to put in cache
     */
    override fun putBitmap(url: String, bitmap: Bitmap) {
        cache.put(url, bitmap)
    }

    companion object {

        /**
         * Maximum number of entries in the cache
         */
        private const val MAX_NUMBER_OF_ENTRIES = 20
    }
}
