package com.santosdaniel.mymechanicagenda.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.santosdaniel.mymechanicagenda.R;

/**
 * Activity that has all the logic used by all the activities of the application
 */
public abstract class GenericActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;

    /**
     * Prevent the crash due to animations when the user go to the activity back.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finishAfterTransition() {
        finish();
    }


    /**
     * Set the title title of the activity and set the toolbar (if any)
     *
     * @param titleId   Identifier of the title to use
     */
    protected void setToolbarWithTitle(int titleId) {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.searchView = (SearchView)findViewById(R.id.search_view);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            this.toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                    searchView.setVisibility(View.VISIBLE);
                }
            });
            this.searchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                    toolbar.setVisibility(View.VISIBLE);
                }
            });

        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return true;
            }
        });


        setTitle(titleId);
    }
}

