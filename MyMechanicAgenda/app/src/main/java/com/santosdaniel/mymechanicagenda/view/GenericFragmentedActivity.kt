package com.santosdaniel.mymechanicagenda.view


/**
 * Expose useful methods to activities that are composed by several fragments
 */
abstract class GenericFragmentedActivity<T> : GenericActivity<T>() {

    /**
     * Set the model in the fragment with a certain identifier
     * @fragmentId  Identifier of the fragment to set the model
     * @model       Reference to the model to set
     */
    private fun setFragmentModel(fragmentId: Int, model: T) {
        val fragmentRef = supportFragmentManager.findFragmentById(fragmentId)
        if (fragmentRef != null) {
            if (fragmentRef is IGenericStateView<*>) {
                (fragmentRef as IGenericStateView<T>).setState(model)
            }
        }
    }

    /**
     * Set the model in the fragments that make part of the activity
     *
     * @param fragmentIds Identifiers of the fragments that compose the activity
     * @param model       The model to set in the fragments
     *
     */
    protected fun setFragmentsModel(fragmentIds: Array<Int>, model: T) {
        if ((fragmentIds != null) && (fragmentIds.isNotEmpty()) && (model != null)) {
            fragmentIds.forEach { setFragmentModel(it, model) }
        }
    }
}