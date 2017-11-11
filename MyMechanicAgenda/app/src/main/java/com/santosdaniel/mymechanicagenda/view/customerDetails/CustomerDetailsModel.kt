package com.santosdaniel.mymechanicagenda.view.customerDetails

import java.io.Serializable

/**
 * Model used by the contact details activity
 */
class CustomerDetailsModel : Serializable {

    /**
     * The lookupId of the contact
     */
    var lookupId: String? = null

    /**
     * Title of the customer
     */
    var title: String? = null

    /**
     * Uri to the image of the customer
     */
    var imageUri: String? = null

    companion object {
        private const val serialVersionUID = -5117894820028821060L
    }
}
