package com.santosdaniel.mymechanicagenda.view.viewModel

/**
 * Represents all the information to show an item in the recycle view
 */

data class GenericListItem(

        /**
         * Identifier of the item
         */
        var id: Long = 0,

        /**
         * The lookupKey used
         */
        var lookUpKey: String? = null,

        /**
         * Uri to the image of the item
         */
        var imageUri: String? = null,

        /**
         * Url with details of the item
         */
        var thumbnailUri: String? = null,

        /**
         * Title of the item
         */
        var title: String? = null,

        /**
         * Description of the item
         */
        var description: String? = null


)
