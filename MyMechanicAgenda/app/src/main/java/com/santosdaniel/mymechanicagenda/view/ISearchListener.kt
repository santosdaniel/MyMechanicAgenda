package com.santosdaniel.mymechanicagenda.view

interface ISearchListener {

    /**
     * Called when the user submit a query
     */
    fun submitQuery(query: String?)
}