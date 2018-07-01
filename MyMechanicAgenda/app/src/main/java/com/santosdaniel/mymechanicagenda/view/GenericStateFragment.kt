package com.santosdaniel.mymechanicagenda.view

import android.os.Bundle
import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * Generic fragment that contains information about a model
 */
abstract class GenericStateFragment<T> : Fragment(), IGenericStateView<T> {

    /**
     * Reference to the model that the fragment acts upon
     */
    protected var lState: T? = null


    /**
     * Called when information of the fragment needs to be saved
     *
     * @param outState  Bundle where is to save the state
     * @param bundleKey Key of the bundle to save the state
     */
    protected fun onSaveInstanceState(outState: Bundle, bundleKey: String) {
        super.onSaveInstanceState(outState)
        if ((outState != null) && (this.lState != null) && (!outState.containsKey(bundleKey))) {
            outState.putSerializable(bundleKey, this.lState as Serializable)
        }
    }


    /**
     * Set the state of the element
     */
    abstract override fun setState(state: T)

    /**
     * Called when the activity is created and the fragment is recreated
     *
     */
    /**
     * @param savedInstanceState Bundle where is to load the state
     * @param bundleKey          Key of bundle to load the state
     */
    protected fun loadStateFromBundle(savedInstanceState: Bundle?, bundleKey: String) {
        if (savedInstanceState != null) {
            this.lState = savedInstanceState.getSerializable(bundleKey) as T?
            if (this.lState != null) {
                this.setState(this.lState!!)
            }
        }
    }
}
