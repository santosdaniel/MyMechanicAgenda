package com.santosdaniel.mymechanicagenda.presenter.contact_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import static android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Loader for contacts of the user
 */

public class ContactsCursorLoader extends CursorLoader {

    // Defines a variable for the search string
    private static String mSearchString;
    // Defines the array to hold values that replace the ?
    private static String[] mSelectionArgs = { mSearchString };

    public static final String DISPLAY_NAME = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;

    // Defines the text expression
    @SuppressLint("InlinedApi")
    private static final String SELECTION = DISPLAY_NAME + " LIKE ?";

    //Define a constant that contains the columns you want to return from your query
    private static final String[] PROJECTION =
            {
                    Phone._ID,
                    Phone.LOOKUP_KEY,
                    Phone.CONTACT_ID,
                    DISPLAY_NAME,
                    Phone.HAS_PHONE_NUMBER,
                    Phone.PHOTO_URI
            };

    /**
     * Creates an empty unspecified CursorLoader.  You must follow this with
     * calls to {@link #setUri(Uri)}, {@link #setSelection(String)}, etc
     * to specify the query to perform.
     *
     * @param context   Reference to the context where the data is going to be loaded
     */
    public ContactsCursorLoader(Context context) {
        super(  context,
                Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
        );
    }
}
