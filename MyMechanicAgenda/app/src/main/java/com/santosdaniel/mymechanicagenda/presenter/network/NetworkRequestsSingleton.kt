package com.santosdaniel.mymechanicagenda.presenter.network

import android.annotation.SuppressLint
import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

/**
 * Singleton that is going to be responsible for making the
 * network requests
 */
class NetworkRequestsSingleton
/**
 * Constructor of the
 *
 * @param context Context that is going to be used to start the cache
 */
private constructor(
        /**
         * Should be one application context otherwise there is risk of a memory leak
         */
        private val context: Context) {

    /**
     * ImageLoader is a an orchestrator for large numbers of ImageRequests
     */
    /**
     * @return The loader of the images from the network
     */
    val networkImageLoader: ImageLoader

    /**
     * ImageLoader for local images
     */
    /**
     * @return The loader of the images from the local storage
     */
    val localImageLoader: LocalImageLoader
    private var mRequestQueue: RequestQueue? = null


    init {
        this.mRequestQueue = requestQueue

        val imageCache = ImageLoaderCache()

        this.localImageLoader = LocalImageLoader(context, imageCache)
        this.networkImageLoader = ImageLoader(mRequestQueue, imageCache)
    }

    /**
     * @return The queue of requests
     */
    private // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(context.applicationContext)
                return mRequestQueue as RequestQueue
            } else {
                return mRequestQueue as RequestQueue
            }
        }

    /**
     * Adds one request to the requests queue
     *
     * @param request The request to add in the ques
     * @param <T>     Type of object to make the request
    </T> */
    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mInstance: NetworkRequestsSingleton? = null

        /**
         * Get the instance of the network singleton
         *
         * @param context The application context
         * @return the instance of the network requests class
         */
        @Synchronized
        fun getInstance(context: Context): NetworkRequestsSingleton {
            if (mInstance == null) {
                mInstance = NetworkRequestsSingleton(context)
                return mInstance as NetworkRequestsSingleton
            } else {
                return mInstance as NetworkRequestsSingleton
            }

        }
    }
}

