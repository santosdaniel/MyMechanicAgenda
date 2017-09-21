package com.santosdaniel.mymechanicagenda.view.contact_list

import android.os.Bundle
import android.view.Menu

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.view.GenericActivity

/**
 * Activity used to show contacts to the user
 */
class ContactListActivity : GenericActivity<ContactListModel>() {

    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState
     */
    private fun setModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val model = ContactListModel()
            super.model = model
        }
    }

    /**
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setToolbarWithTitle(R.string.phone_book, true)
        setModel(savedInstanceState)
    }


    /**
     * Called the the activity is resumed
     */
    override fun onResume() = super.onResume()

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu the reference to the menu to inflate
     * @return if the menu was inflated or not
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_contacts, menu)
        return true
    }


    /**
     * This callback is called only when there is a saved instance previously saved using
     * onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
     * other state here, possibly usable after onStart() has completed.
     * The savedInstanceState Bundle is same as the one used in onCreate().
     *
     * @param savedInstanceState    The state previously saved
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) =
            super.onRestoreInstanceState(savedInstanceState)

}
