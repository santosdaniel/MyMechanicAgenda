package com.santosdaniel.mymechanicagenda.view.contact_list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.view.GenericActivity;

/**
 * Activity used to show contacts to the user
 */
public class ContactListActivity extends GenericActivity<ContactListModel> {

    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState
     */
    private void setModel(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            ContactListModel model = new ContactListModel();
            //TODO: set information in the mode
            setModel(model);
        }
    }

    /**
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setToolbarWithTitle(R.string.phonebook, true);
        setModel(savedInstanceState);
    }


    /**
     * Called the the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        //TODO: if a model is selected launch the contact details activity
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu the reference to the menu to inflate
     * @return if the menu was inflated or not
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }


    /**
     * This callback is called only when there is a saved instance previously saved using
     * onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
     * other state here, possibly usable after onStart() has completed.
     * The savedInstanceState Bundle is same as the one used in onCreate().
     *
     * @param savedInstanceState    The state previously saved
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
