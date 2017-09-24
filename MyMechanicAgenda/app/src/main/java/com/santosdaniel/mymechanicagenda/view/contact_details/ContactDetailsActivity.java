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

    public static final String TAG = "DaoExample";
    private EditText editText;
    private View addNoteButton;

    private ContactRepository contactRepository;
    private NotesAdapter notesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        setToolbarWithTitle(R.string.phone_book, true);

        setUpViews();

        IDaoSessionProvider daoSessionProvider = ((IDaoSessionProvider) getApplication());
        this.contactRepository = new ContactRepository(daoSessionProvider);


        updateNotes();
    }

    private void updateNotes() {
        List<Note> notes = this.contactRepository.getAllContacts();
        notesAdapter.setNotes(notes);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter(noteClickListener);
        recyclerView.setAdapter(notesAdapter);

        addNoteButton = findViewById(R.id.buttonAdd);
        //noinspection ConstantConditions
        addNoteButton.setEnabled(false);

        editText = (EditText) findViewById(R.id.editTextNote);
        editText.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                addNoteButton.setEnabled(enable);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onAddButtonClick(View view) {
        addNote();
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText(noteText);
        note.setComment(comment);
        note.setDate(new Date());
        note.setType(NoteType.TEXT);
        this.contactRepository.create(note);
        Log.d(TAG, "Inserted new note, ID: " + note.getId());

        updateNotes();
    }

    NotesAdapter.NoteClickListener noteClickListener = new NotesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = notesAdapter.getNote(position);
            Long noteId = note.getId();

            contactRepository.deleteById(noteId);
            Log.d(TAG, "Deleted note, ID: " + noteId);

            updateNotes();
        }
    };
}
