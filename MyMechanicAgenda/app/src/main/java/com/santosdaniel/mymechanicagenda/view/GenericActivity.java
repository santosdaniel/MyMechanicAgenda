package com.santosdaniel.mymechanicagenda.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.santosdaniel.mymechanicagenda.R;
import com.santosdaniel.mymechanicagenda.helper.ToolbarSearchViewHelper;
import com.santosdaniel.mymechanicagenda.helper.UIHelper;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity that has all the logic used by all the activities of the application
 *
 * @param <T> Type of model to use
 */
public abstract class GenericActivity<T> extends AppCompatActivity {

    private static final String G_MODEL_KEY = "gActivity_model_key";
    private static final String MODEL_KEY = "activity_model_key";


    protected Toolbar toolbar;

    private View searchSection;
    private ImageView searchQuit;
    private SearchView searchView;
    private List<WeakReference<Fragment>> fragmentList;

    /**
     * Model to be keep by the with information only of the generic activity (Not children)
     */
    private GenericActivityModel genericModel;

    /**
     * Model defined by the child class
     */
    private T model;

    /**
     * Prevent the crash due to animations when the user go to the activity back.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finishAfterTransition() {
        finish();
    }

    /**
     * Initializes the model that is going to use in the activity
     *
     * @param savedInstanceState Has the bundle previous passed
     */
    private void setModel(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            GenericActivityModel genericModel = new GenericActivityModel();
            genericModel.isSearching = false;
            this.genericModel = genericModel;
        }
    }

    /**
     * Perform initialization of all fragments and loaders.
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(savedInstanceState);
        this.fragmentList = new ArrayList<>();
    }

    /**
     * This callback is called only when there is a saved instance previously saved using
     * onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
     * other state here, possibly usable after onStart() has completed.
     * The savedInstanceState Bundle is same as the one used in onCreate().
     *
     * @param savedInstanceState The state previously saved
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getSerializable(MODEL_KEY) == null) {
            this.model = null;
        } else {
            this.model = (T) savedInstanceState.getSerializable(MODEL_KEY);
        }
        if (savedInstanceState.getSerializable(G_MODEL_KEY) == null) {
            this.genericModel = null;
        } else {
            this.genericModel = (GenericActivityModel) savedInstanceState.getSerializable(G_MODEL_KEY);
        }
    }

    /**
     * Called the the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        ToolbarSearchViewHelper.showHideSearchView(this.genericModel.isSearching, this.toolbar, this.searchSection);
    }

    /**
     * Called when a fragment is attached to the activity.
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.fragmentList.add(new WeakReference(fragment));
    }

    /**
     * Set the title title of the activity and set the toolbar (if any)
     *
     * @param titleId    Identifier of the title to use
     * @param withSearch Flag that indicates if the activity supports searchBar or not
     */
    protected void setToolbarWithTitle(int titleId, boolean withSearch) {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(toolbar);
        }
        this.searchSection = findViewById(R.id.search_section);
        if (this.searchSection != null) {
            if (withSearch) {
                //Quits the search mode of the view
                SearchQuitOnClickListener searchQuitOnClickListener = new SearchQuitOnClickListener(this.toolbar, this.searchSection, this.genericModel);
                this.searchQuit = (ImageView) findViewById(R.id.search_quit);
                this.searchQuit.setOnClickListener(searchQuitOnClickListener);
                //TODO: search quit
                //Set the search view
                this.searchView = (SearchView) findViewById(R.id.search_view);
                if (searchView != null) {
                    SearchViewDelegator searchViewDelegator = new SearchViewDelegator(this, this.fragmentList);
                    searchView.setOnQueryTextListener(searchViewDelegator);
                }
            } else {
                //If is to not search is going to hide the search section
                UIHelper.setVisibility(View.GONE, this.searchSection);
            }
        }
        setTitle(titleId);
    }

    /**
     * Triggered when the user click in one item of the menu
     *
     * @param item The item that the user selected
     * @return If the event was handled or not
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                this.genericModel.isSearching = true;
                ToolbarSearchViewHelper.showHideSearchView(this.genericModel.isSearching, this.toolbar, this.searchSection);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Set the model of the activity
     *
     * @param model Reference to the model to set
     */
    protected void setModel(T model) {
        this.model = model;
    }

    /**
     * @return The model that currently is pointing
     */
    protected T getModel() {
        return model;
    }

    /**
     * @param outState Set the model in the bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (model instanceof Serializable) {
            outState.putSerializable(MODEL_KEY, (Serializable) model);
        } else {
            outState.putSerializable(MODEL_KEY, null);
        }
        if (this.genericModel instanceof Serializable) {
            outState.putSerializable(G_MODEL_KEY, this.genericModel);
        }
        super.onSaveInstanceState(outState);
    }


}

