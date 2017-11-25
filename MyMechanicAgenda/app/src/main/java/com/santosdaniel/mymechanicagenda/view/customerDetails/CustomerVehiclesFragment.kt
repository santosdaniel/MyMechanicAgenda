package com.santosdaniel.mymechanicagenda.view.customerDetails

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.helper.ViewHelper
import com.santosdaniel.mymechanicagenda.model.database.Customer
import com.santosdaniel.mymechanicagenda.presenter.customerDetails.CustomerRepository
import com.santosdaniel.mymechanicagenda.presenter.customerDetails.VehiclesAdapter
import com.santosdaniel.mymechanicagenda.view.GenericRecycleViewFragment
import com.santosdaniel.mymechanicagenda.view.IGenericStateView
import java.io.Serializable

/**
 * Activity to present the contact with a list of vehicles
 */
class CustomerVehiclesFragment : GenericRecycleViewFragment<VehiclesAdapter>(), IGenericStateView<CustomerDetailsModel>, QueryTransaction.QueryResultSingleCallback<Customer> {

    private var customerPicture: ImageView? = null

    private var customerRepository: CustomerRepository? = null
    private var vehiclesAdapter: VehiclesAdapter? = null
    private var contactData: CustomerDetailsModel? = null
    private var customer: Customer? = null

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        super.lstResults = fragmentView.findViewById(R.id.items_list)
        super.loadProgress = fragmentView.findViewById(R.id.load_progress)
        this.customerPicture = fragmentView.findViewById(R.id.tool_bar_picture)

        if (super.lstResults != null) {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            super.lstResults!!.setHasFixedSize(false)

            // use a linear layout manager
            super.lstLayoutManager = LinearLayoutManager(context)
            super.lstResults!!.layoutManager = lstLayoutManager


            this.vehiclesAdapter = VehiclesAdapter(activity!!, super.lstResults!!, super.loadProgress!!)
            this.lstResults!!.adapter = vehiclesAdapter
        }
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.generic_progress_list_fragment, container, false)
        bindViews(fragmentView)

        this.customerRepository = CustomerRepository()

        return fragmentView
    }

    /**
     *
     * @param customer  The customer is set it
     */
    private fun setCustomer(customer: Customer?) {
        loadProgress?.visibility = View.GONE
        if (customer == null) {
            this.customer = Customer()
        } else {
            this.customer = customer
        }
        this.vehiclesAdapter!!.setVehicles(this.customer!!.vehicles)
        //Set the temporary data into the customer data
        this.customer!!.lookup = this.contactData!!.lookupId
        this.customer!!.fullName = this.contactData!!.title
    }

    /**
     * Called when the query completes.
     *
     * @param transaction The transaction that ran.
     * @param customer    The single result of the query (If any).
     */
    override fun onSingleQueryResult(transaction: QueryTransaction<*>?, customer: Customer?) {
        this.setCustomer(customer)
    }

    /**
     * Load the customer from that has the loopId passed
     *
     * @param lookupId The lookup of the contact
     */
    private fun loadCustomer(lookupId: String?) {
        if (StringHelper.isNotNullOrEmpty(lookupId)) {
            this.customerRepository!!.loadByLookId(lookupId!!, this)
        }
    }

    /**
     * Load the image of the customer
     */
    private fun loadThumbnail(imageUri: String?) {
        //Load the image of the contact
        if (StringHelper.isNotNullOrEmpty(imageUri)) {
            ViewHelper.loadImageOrDefault(activity!!, imageUri, R.mipmap.person, customerPicture)
        }
    }

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: CustomerDetailsModel) {
        this.contactData = state
        loadCustomer(state.lookupId)
        loadThumbnail(state.imageUri)
    }


    /**
     * Called when information of the fragment needs to be saved
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            //Save the contactData
            if (this.contactData != null) {
                outState.putSerializable(CONTACT_DATA_KEY, this.contactData as Serializable)
            }
            //Save the costumer
            if (this.customer != null) {
                outState.putSerializable(CUSTOMER_KEY, this.customer as Serializable)
            }
        }

    }

    /**
     * Called when the activity is created and the fragment is recreated
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            this.contactData = savedInstanceState.getSerializable(CONTACT_DATA_KEY) as CustomerDetailsModel?
            this.customer = savedInstanceState.getSerializable(CUSTOMER_KEY) as Customer?
            if (this.contactData != null) {
                if (this.customer == null) {
                    this.setState(this.contactData!!)
                } else {
                    this.setCustomer(this.customer)
                }
            }
        }
    }

    companion object {

        val TAG = "CustomerVehiclesFragment"
        val CONTACT_DATA_KEY = "contactData"
        val CUSTOMER_KEY = "customer"
    }


    /*
    VehiclesAdapter.NoteClickListener noteClickListener = new VehiclesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = vehiclesAdapter.getNote(position);
            Long noteId = note.getId();

            //customerRepository.deleteById(noteId);
            Log.d(TAG, "Deleted note, ID: " + noteId);

            updateNotes();
        }
    };
    */
}
