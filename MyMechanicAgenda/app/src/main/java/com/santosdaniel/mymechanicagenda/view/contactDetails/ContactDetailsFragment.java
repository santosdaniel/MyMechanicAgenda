package com.santosdaniel.mymechanicagenda.view.contactDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.model.database.Note;
import com.santosdaniel.mymechanicagenda.model.database.NoteType;
import com.santosdaniel.mymechanicagenda.presenter.contactDetails.ContactRepository;
import com.santosdaniel.mymechanicagenda.presenter.contactDetails.VehiclesAdapter;
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.IDaoSessionProvider;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import android.support.v4.app.Fragment;

/**
 * Activity to present the contact with a list of vehicles
 */
public class ContactDetailsFragment extends Fragment {

    public static final String TAG = "DaoExample";
    private EditText editText;
    private View addNoteButton;

    private ContactRepository contactRepository;
    private VehiclesAdapter vehiclesAdapter;

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private void bindViews(View fragmentView) {
        RecyclerView recyclerView = fragmentView.findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        vehiclesAdapter = new VehiclesAdapter(noteClickListener);
        recyclerView.setAdapter(vehiclesAdapter);

        addNoteButton = fragmentView.findViewById(R.id.buttonAdd);
        //noinspection ConstantConditions
        addNoteButton.setEnabled(false);

        editText = (EditText) fragmentView.findViewById(R.id.editTextNote);
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

    /**
     * @param inflater  The inflater of the view
     * @param container The container where is to inflate the fragment
     * @param savedInstanceState    The state previously saved
     *
     * @return  The view that is going support the fragment
     */
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_contact_details, container, false);
        bindViews(fragmentView);

        IDaoSessionProvider daoSessionProvider = ((IDaoSessionProvider) getActivity().getApplication());
        this.contactRepository = new ContactRepository(daoSessionProvider);

        updateNotes();
        return fragmentView;
    }

    private void updateNotes() {
        List<Note> notes = this.contactRepository.getAllContacts();
        vehiclesAdapter.setNotes(notes);
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

    VehiclesAdapter.NoteClickListener noteClickListener = new VehiclesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = vehiclesAdapter.getNote(position);
            Long noteId = note.getId();

            contactRepository.deleteById(noteId);
            Log.d(TAG, "Deleted note, ID: " + noteId);

            updateNotes();
        }
    };
}
