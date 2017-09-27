package com.santosdaniel.mymechanicagenda.view.contact_details;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.model.database.Note;
import com.santosdaniel.mymechanicagenda.model.database.NoteType;
import com.santosdaniel.mymechanicagenda.presenter.contact_details.ContactRepository;
import com.santosdaniel.mymechanicagenda.presenter.contact_details.NotesAdapter;
import com.santosdaniel.mymechanicagenda.presenter.generic_database.IDaoSessionProvider;
import com.santosdaniel.mymechanicagenda.view.GenericActivity;
import com.santosdaniel.mymechanicagenda.view.contact_list.ContactListModel;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Activity to present the contact with a list of vehicles
 */
public class ContactDetailsActivity extends GenericActivity<ContactListModel> {


    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    private void setModel(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ContactListModel model = new ContactListModel();
            super.setModel(model);
        }
    }

    /**
     * Called on create of the activity
     *
     * @param savedInstanceState    object containing the activity's previously saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        setToolbarWithTitle(R.string.phone_book, false);
        setModel(savedInstanceState);
    }
}
