package com.santosdaniel.mymechanicagenda.view.customerDetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.model.database.Customer
import com.santosdaniel.mymechanicagenda.view.IGenericStateView
import com.santosdaniel.mymechanicagenda.view.generic.ViewHelper
import java.io.Serializable

/**
 * Activity to present the contact with a list of vehicles
 */
class CustomerPictureFragment : Fragment(), IGenericStateView<CustomerDetailsModel> {

    private var customerPicture: ImageView? = null

    private var contactData: CustomerDetailsModel? = null
    private var customer: Customer? = null

    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        this.customerPicture = fragmentView.findViewById(R.id.tool_bar_picture)
    }

    /**
     * @param inflater           The inflater of the view
     * @param container          The container where is to inflate the fragment
     * @param savedInstanceState The state previously saved
     * @return The view that is going support the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater!!.inflate(R.layout.collapsing_image_toolbar, container, false)
        bindViews(fragmentView)
        return fragmentView
    }


    /**
     * Load the image of the customer
     */
    private fun loadPicture(state: CustomerDetailsModel) {
        val imageUri: String? = state.imageUri
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
        loadPicture(state)
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
                    this.loadPicture(this.contactData!!)
                }
            }
        }
    }

    companion object {
        val TAG = "CustomerPictureFragment"
        val CONTACT_DATA_KEY = "contactData"
        val CUSTOMER_KEY = "customer"
    }
}
