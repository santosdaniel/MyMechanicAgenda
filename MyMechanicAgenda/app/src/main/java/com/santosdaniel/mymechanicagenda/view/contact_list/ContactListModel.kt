package com.santosdaniel.mymechanicagenda.view.contact_list

import java.io.Serializable

/**
 * Model used by the contact list activity
 */
class ContactListModel : Serializable {

    var selectedContact: String? = null

    companion object {

        private const val serialVersionUID = -5384008215168462990L
    }
}
