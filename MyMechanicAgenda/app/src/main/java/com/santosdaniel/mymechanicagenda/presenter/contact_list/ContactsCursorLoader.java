package com.santosdaniel.mymechanicagenda.presenter.contact_list;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.text.TextUtils;
import static android.provider.ContactsContract.Contacts;

import static android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Loader for contacts of the user
 */
public class ContactsCursorLoader extends CursorLoader {
    public static final String NAME_ATTR = "name";


    public static final String DISPLAY_NAME = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
            Contacts.DISPLAY_NAME_PRIMARY :
            Contacts.DISPLAY_NAME;


    //Define a constant that contains the columns you want to return from your query
    private static final String[] PROJECTION =
            {
                    Contacts._ID,
                    Contacts.LOOKUP_KEY,
                    DISPLAY_NAME,
                    Contacts.HAS_PHONE_NUMBER,
                    Contacts.PHOTO_URI
            };

    /**
     *
     * @param bundle
     * @return
     */
    private static String getSelectionString(Bundle bundle) {
        if ((bundle == null) || (TextUtils.isEmpty(bundle.getString(NAME_ATTR)))) {
            return null;
        } else {
            return DISPLAY_NAME + " LIKE ?";
        }
    }

    /**
     *
     * @param bundle
     * @return
     */
    private static String[] getSelectionArgs(Bundle bundle) {
        if ((bundle == null) || (TextUtils.isEmpty(bundle.getString(NAME_ATTR)))) {
            return null;
        } else {
            String[] selectionArgs = { "%" + bundle.getString(NAME_ATTR) + "%"};
            return selectionArgs;
        }
    }

    /**
     * Makes the contacts to be sorted by name
     */
    private static final String SORT_ORDER = DISPLAY_NAME + " ASC";

    /**
     * Creates an empty unspecified CursorLoader.
     *
     * @param context   Reference to the context where the data is going to be loaded
     */
    public ContactsCursorLoader(Context context, Bundle bundle) {
        super(  context,
                Contacts.CONTENT_URI,
                PROJECTION,
                getSelectionString(bundle),
                getSelectionArgs(bundle),
                SORT_ORDER
        );
    }
}
