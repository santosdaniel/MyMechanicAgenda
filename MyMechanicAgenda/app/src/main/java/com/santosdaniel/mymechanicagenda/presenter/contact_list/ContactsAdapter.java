package com.santosdaniel.mymechanicagenda.presenter.contact_list;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.santosdaniel.mymechanicagenda.presenter.GenericRViewCursorAdapter;
import com.santosdaniel.mymechanicagenda.presenter.ListItemViewHolder;
import com.santosdaniel.mymechanicagenda.view.GenericListItem;

import static android.provider.ContactsContract.Contacts;

/**
 * Adapter of the list of elements
 */
public class ContactsAdapter extends GenericRViewCursorAdapter {


    /**
     * Constructor to the view adapter
     *
     * @param activity     Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    public ContactsAdapter(@NonNull Activity activity, RecyclerView recyclerView, ProgressBar progressBar) {
        super(activity, recyclerView, progressBar);
    }

    /**
     * @param viewHolder reference to the view holder that support the item
     * @param cursor     cursor from where the data should be fetch
     * @return The GenericListItem associated with current cursor
     */
    @Override
    public GenericListItem getGenericListItem(ListItemViewHolder viewHolder, Cursor cursor) {
        if (viewHolder.data == null) {
            viewHolder.data = new GenericListItem();
        }
        GenericListItem data = viewHolder.data;

        //Read indexes
        int idIndex = cursor.getColumnIndex(Contacts._ID);
        int lookupKeyIndex = cursor.getColumnIndex(Contacts.LOOKUP_KEY);
        int displayNameIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY);
        int hasPhoneNumberIndex = cursor.getColumnIndex(Contacts.HAS_PHONE_NUMBER);
        int thumbnailUriIndex = cursor.getColumnIndex(Contacts.PHOTO_THUMBNAIL_URI);
        int sourceIdIndex = cursor.getColumnIndex(ContactsContract.RawContacts.SOURCE_ID );

        //Read data
        long id = cursor.getLong(idIndex);
        String lookupKey = cursor.getString(lookupKeyIndex);
        String displayName = cursor.getString(displayNameIndex);
        boolean hasPhoneNumber = cursor.getInt(hasPhoneNumberIndex) == 1;
        String thumbnailUri = cursor.getString(thumbnailUriIndex);

        data.title = id + "";
        data.imageUri = thumbnailUri;


        return data;
    }


    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View v) {
        GenericListItem item = getItemByView(v);
        if ((item != null) && (!TextUtils.isEmpty(item.detailsUrl))) {
            //TODO:
            /*
            Intent intent = new Intent(activity, ReceiptDetailsActivity.class);
            intent.putExtra(ReceiptDetailsActivity.TITLE, item.title);
            intent.putExtra(ReceiptDetailsActivity.URL, item.detailsUrl);
            IntentHelper.startNewActivity(activity, v, intent);
            */
        }
    }

    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    @Override
    public void startLoading() {
    }

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    @Override
    public void stopLoading() {
    }

}
