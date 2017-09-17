package com.santosdaniel.mymechanicagenda.view

import java.io.Serializable

/**
 * Contains the model that is common to all the activities of the Application
 */
class GenericActivityModel : Serializable {

    /**
     * Indicates if the activity has the search option or not
     */
    var withSearch: Boolean = false

    /**
     * Flag indicating if the user is currently searching
     */
    var isSearching: Boolean = false

    companion object {
        private const val serialVersionUID = 1468709520908365610L
    }
}
