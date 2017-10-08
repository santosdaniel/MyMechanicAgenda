package com.santosdaniel.mymechanicagenda.view.vehicleDetails;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.santosdaniel.mymechanicagenda.R;

import java.text.DateFormat;
import java.util.Date;

/**
 * Fragment that contains the details of the vehicle to show
 */

public class VehicleDetailsFragment extends Fragment {

    private EditText editText;
    private View addNoteButton;


    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private void bindViews(View fragmentView) {
        addNoteButton = fragmentView.findViewById(R.id.buttonAdd);
        //noinspection ConstantConditions
        addNoteButton.setEnabled(false);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicle();
            }
        });

        editText = (EditText) fragmentView.findViewById(R.id.editTextNote);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addVehicle();
                    return true;
                }
                return false;
            }
        });
    }

    private void addVehicle() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
    }
}
