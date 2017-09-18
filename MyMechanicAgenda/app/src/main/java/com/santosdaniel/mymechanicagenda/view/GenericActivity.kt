package com.santosdaniel.mymechanicagenda.view

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.ToolbarSearchViewHelper
import com.santosdaniel.mymechanicagenda.helper.UIHelper

import java.io.Serializable
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Activity that has all the logic used by all the activities of the application
 *
 * @param <T> Type of model to use
</T> */
abstract class GenericActivity<T> : AppCompatActivity() {


    private var toolbar: Toolbar? = null

    private var searchSection: View? = null
    private var searchQuit: ImageView? = null
    private var searchView: SearchView? = null
    private var fragmentList: MutableList<WeakReference<Fragment>>? = null

    /**
     * Model to be keep by the with information only of the generic activity (Not children)
     */
    private var genericModel: GenericActivityModel? = null

    /**
     * Indicates if supports search or not
     */
    private var withSearch: Boolean = false

    /**
     * Model defined by the child class
     */
    protected var model: T? = null

    /**
     * Prevent the crash due to animations when the user go to the activity back.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun finishAfterTransition() {
        finish()
    }

    /**
     * Initializes the model that is going to use in the activity
     *
     */
    private fun setModel() {
        if (genericModel == null) {
            val genericModel = GenericActivityModel()
            genericModel.withSearch = this.withSearch
            genericModel.isSearching = false
            this.genericModel = genericModel
        }
    }

    /**
     * Perform initialization of all fragments and loaders.
     *
     * @param savedInstanceState object containing the activity's previously saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.fragmentList = ArrayList()
    }

    /**
     * This callback is called only when there is a saved instance previously saved using
     * onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
     * other state here, possibly usable after onStart() has completed.
     * The savedInstanceState Bundle is same as the one used in onCreate().
     *
     * @param savedInstanceState The state previously saved
     */
    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getSerializable(MODEL_KEY) == null) {
            this.model = null
        } else {
            this.model = savedInstanceState.getSerializable(MODEL_KEY) as T
        }
        if (savedInstanceState.getSerializable(G_MODEL_KEY) == null) {
            this.genericModel = null
        } else {
            this.genericModel = savedInstanceState.getSerializable(G_MODEL_KEY) as GenericActivityModel
        }
    }

    /**
     * Bind the events of the search view
     */
    private fun bindSearchView() {
        this.searchView = findViewById(R.id.search_view) as SearchView
        if (searchView != null) {
            val searchViewDelegator = SearchViewDelegator(this, this.fragmentList)
            searchView!!.setOnQueryTextListener(searchViewDelegator)
            if (this.genericModel!!.isSearching) {

                searchView!!.setQuery(searchView!!.query, true)
            }
        }
    }

    /**
     * Associates the search views with search actions
     */
    private fun bindToolbarViews() {
        this.searchSection = findViewById(R.id.search_section)
        if (this.searchSection != null) {
            if (this.genericModel!!.withSearch) {
                //Set the search view
                bindSearchView()
                //Quits the search mode of the view
                val searchQuitOnClickListener = SearchQuitOnClickListener(this.toolbar!!, this.searchSection!!, this.searchView!!, this.genericModel!!)
                this.searchQuit = findViewById(R.id.search_quit) as ImageView
                this.searchQuit!!.setOnClickListener(searchQuitOnClickListener)
            } else {
                //If is to not search is going to hide the search section
                UIHelper.setVisibility(View.GONE, this.searchSection!!)
            }
        }
    }


    /**
     * Called the the activity is resumed
     */
    override fun onResume() {
        setModel()
        super.onResume()
        bindToolbarViews()
        ToolbarSearchViewHelper.showHideSearchView(this.genericModel!!.isSearching, this.toolbar!!, this.searchSection!!)
    }

    /**
     * Called when a fragment is attached to the activity.
     */
    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        if(fragment != null) {
            this.fragmentList!!.add(WeakReference(fragment))
        }
    }

    /**
     * Set the title title of the activity and set the toolbar (if any)
     *
     * @param titleId    Identifier of the title to use
     * @param withSearch Flag that indicates if the activity supports searchBar or not
     */
    protected fun setToolbarWithTitle(titleId: Int, withSearch: Boolean) {
        this.toolbar = findViewById(R.id.toolbar) as Toolbar
        if (this.toolbar != null) {
            setSupportActionBar(toolbar)
        }
        this.withSearch = withSearch
        setTitle(titleId)
    }

    /**
     * Triggered when the user click in one item of the menu
     *
     * @param item The item that the user selected
     * @return If the event was handled or not
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
            R.id.action_search -> {
                this.genericModel!!.isSearching = true
                ToolbarSearchViewHelper.showHideSearchView(this.genericModel!!.isSearching, this.toolbar!!, this.searchSection!!)
                searchView!!.isFocusable = true
                searchView!!.isIconified = false
                searchView!!.requestFocusFromTouch()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * @param outState Set the model in the bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        if (model is Serializable) {
            outState.putSerializable(MODEL_KEY, model as Serializable?)
        } else {
            outState.putSerializable(MODEL_KEY, null)
        }
        if (this.genericModel is Serializable) {
            outState.putSerializable(G_MODEL_KEY, this.genericModel)
        }
        super.onSaveInstanceState(outState)
    }

    companion object {

        private val G_MODEL_KEY = "gActivity_model_key"
        private val MODEL_KEY = "activity_model_key"
    }


}

