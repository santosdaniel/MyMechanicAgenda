package com.santosdaniel.mymechanicagenda.view.contactDetails

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.model.database.Costumer
import com.santosdaniel.mymechanicagenda.presenter.costumerDetails.CostumerRepository
import com.santosdaniel.mymechanicagenda.presenter.costumerDetails.VehiclesAdapter
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment
import com.santosdaniel.mymechanicagenda.view.IGenericStateView

/**
 * Activity to present the contact with a list of vehicles
 */
class ContactDetailsFragment : GenericRecycleViewFragment<VehiclesAdapter>(), IGenericStateView<ContactDetailsModel> {

    private var costumerRepository: CostumerRepository? = null
    private val vehiclesAdapter: VehiclesAdapter? = null

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        super.lstResults = fragmentView.findViewById<View>(R.id.items_list) as RecyclerView
        super.loadProgress = fragmentView.findViewById<View>(R.id.load_progress) as ProgressBar

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        super.lstResults!!.setHasFixedSize(false)

        // use a linear layout manager
        super.lstLayoutManager = LinearLayoutManager(context)
        super.lstResults!!.layoutManager = lstLayoutManager


        //vehiclesAdapter = new VehiclesAdapter(noteClickListener);
        //recyclerView.setAdapter(vehiclesAdapter);
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.fragment_contact_details, container, false)
        bindViews(fragmentView)

        this.costumerRepository = CostumerRepository()

        return fragmentView
    }

    /**
     * Load the costumer from that has the loopId passed
     *
     * @param lookupId The lookup of the contact
     */
    private fun loadCostumer(lookupId: String) {
        var costumer = this.costumerRepository!!.loadByLookId(lookupId)
        if (costumer == null) {
            costumer = Costumer()
        } else {
            vehiclesAdapter!!.setVehicles(costumer.vehicles)
        }

    }

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: ContactDetailsModel) {
        loadCostumer(state.lookupId)
    }

    companion object {

        val TAG = "ContactDetailsFragment"
    }


    /*
    VehiclesAdapter.NoteClickListener noteClickListener = new VehiclesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = vehiclesAdapter.getNote(position);
            Long noteId = note.getId();

            //costumerRepository.deleteById(noteId);
            Log.d(TAG, "Deleted note, ID: " + noteId);

            updateNotes();
        }
    };
    */
}
