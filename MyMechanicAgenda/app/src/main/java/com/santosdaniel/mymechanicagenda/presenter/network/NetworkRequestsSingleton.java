package com.santosdaniel.mymechanicagenda.presenter.network;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Singleton that is going to be responsible for making the
 * network requests
 */
public class NetworkRequestsSingleton {
    @SuppressLint("StaticFieldLeak")
    private static NetworkRequestsSingleton mInstance;

    /**
     * ImageLoader is a an orchestrator for large numbers of ImageRequests
     */
    private final ImageLoader networkImageLoader;

    /**
     * ImageLoader for local images
     */
    private LocalImageLoader localImageLoader;

    /**
     * Should be one application context otherwise there is risk of a memory leak
     */
    private final Context context;
    private RequestQueue mRequestQueue;


    /**
     * Constructor of the
     *
     * @param context Context that is going to be used to start the cache
     */
    private NetworkRequestsSingleton(Context context) {
        this.context = context;
        this.mRequestQueue = getRequestQueue();

        ImageLoader.ImageCache imageCache = new ImageLoaderCache();

        this.localImageLoader = new LocalImageLoader(context, imageCache);
        this.networkImageLoader = new ImageLoader(mRequestQueue, imageCache);
    }

    /**
     * Get the instance of the network singleton
     *
     * @param context The application context
     * @return the instance of the network requests class
     */
    public static synchronized NetworkRequestsSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkRequestsSingleton(context);
        }
        return mInstance;
    }

    /**
     * @return The queue of requests
     */
    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds one request to the requests queue
     *
     * @param request The request to add in the ques
     * @param <T>     Type of object to make the request
     */
    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * @return The loader of the images from the network
     */
    public ImageLoader getNetworkImageLoader() {
        return networkImageLoader;
    }

    /**
     * @return The loader of the images from the local storage
     */
    public LocalImageLoader getLocalImageLoader() {
        return this.localImageLoader;
    }
}

