package com.santosdaniel.mymechanicagenda.view.contact_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santosdaniel.mymechanicagenda.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactListFragment extends Fragment {

    /**
     * Default constructor to the fragment
     */
    public ContactListFragment() {
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }
}
