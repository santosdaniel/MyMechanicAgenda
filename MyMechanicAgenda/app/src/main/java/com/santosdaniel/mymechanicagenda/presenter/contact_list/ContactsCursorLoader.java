package com.santosdaniel.mymechanicagenda.presenter.contact_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.text.TextUtils;

import static android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Loader for contacts of the user
 */
public class ContactsCursorLoader extends CursorLoader {
    public static final String NAME_ATTR = "name";


    // Defines a variable for the search string
    private static String mSearchString;
    // Defines the array to hold values that replace the ?
    private static String[] mSelectionArgs = { mSearchString };

    public static final String DISPLAY_NAME = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;


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

    private static String getSelectionString(Bundle bundle) {
        if ((bundle == null) || (TextUtils.isEmpty(bundle.getString(NAME_ATTR)))) {
            return null;
        } else {
            return DISPLAY_NAME + " LIKE ?";
        }
    }

    private static String[] getSelectionArgs(Bundle bundle) {
        if ((bundle == null) || (TextUtils.isEmpty(bundle.getString(NAME_ATTR)))) {
            return null;
        } else {
            String[] selectionArgs = { "%" + bundle.getString(NAME_ATTR) + "%"};
            return selectionArgs;
        }
    }

    private static final String SORT_ORDER = DISPLAY_NAME + " ASC";

    /**
     * Creates an empty unspecified CursorLoader.  You must follow this with
     * calls to {@link #setUri(Uri)}, {@link #setSelection(String)}, etc
     * to specify the query to perform.
     *
     * @param context   Reference to the context where the data is going to be loaded
     */
    public ContactsCursorLoader(Context context, Bundle bundle) {
        super(  context,
                Phone.CONTENT_URI,
                PROJECTION,
                getSelectionString(bundle),
                getSelectionArgs(bundle),
                SORT_ORDER
        );
    }
}
