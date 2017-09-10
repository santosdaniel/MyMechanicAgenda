package com.santosdaniel.mymechanicagenda.view.contact_list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.view.GenericActivity;

/**
 * Activity used to show contacts to the user
 */
public class ContactListActivity extends GenericActivity {


    /**
     * Called on create of the view
     *
     * @param savedInstanceState the bundle of the instance saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setToolbarWithTitle(R.string.phonebook);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
