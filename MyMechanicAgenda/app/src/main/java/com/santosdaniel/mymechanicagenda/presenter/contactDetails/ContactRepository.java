package com.santosdaniel.mymechanicagenda.presenter.contactDetails;

import android.util.Log;

import com.santosdaniel.mymechanicagenda.helper.DBHelper;
import com.santosdaniel.mymechanicagenda.model.database.DaoSession;
import com.santosdaniel.mymechanicagenda.model.database.Note;
import com.santosdaniel.mymechanicagenda.model.database.NoteDao;
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.GenericRepository;
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.IDaoSessionProvider;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 *  Has the methods to use the contact model
 */
public class ContactRepository extends GenericRepository {

    private static final String TAG = "ContactRepository";
    private NoteDao noteDao;
    private Query<Note> notesQuery;

    public ContactRepository(IDaoSessionProvider iDaoSessionProvider) {
        // get the note DAO
        DaoSession daoSession = iDaoSessionProvider.getDaoSession();
        noteDao = daoSession.getNoteDao();

        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
    }

    public Long create(Note note) {
        try {
            return this.noteDao.insert(note);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public Note loadById(Long id) {
        if(DBHelper.INSTANCE.validId(id)) {
            return null;
        } else {
            //The identifier is not valid to search indatabase
            return null;
        }
    }

    public List<Note> getAllContacts() {
        return notesQuery.list();
    }

    public void deleteById(Long noteId) {
        noteDao.deleteByKey(noteId);
    }
}
