package com.santosdaniel.mymechanicagenda.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity that has all the logic used by all the activities of the application
 */
public abstract class GenericActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private List<WeakReference<Fragment>> fragmentList;

    /**
     * Prevent the crash due to animations when the user go to the activity back.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finishAfterTransition() {
        finish();
    }

    /**
     * Perform initialization of all fragments and loaders.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentList = new ArrayList<>();
    }

    /**
     * Called when a fragment is attached to the activity.
     */
    @Override
    public void onAttachFragment (Fragment fragment) {
        super.onAttachFragment(fragment);
        this.fragmentList.add(new WeakReference(fragment));
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
        SearchViewDelegator searchViewDelegator = new SearchViewDelegator(this, this.fragmentList);
        searchView.setOnQueryTextListener(searchViewDelegator);

        setTitle(titleId);
    }
}

