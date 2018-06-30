package com.santosdaniel.mymechanicagenda.view.vehicleDetails.edit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.santosdaniel.mymechanicagenda.R
import com.santosdaniel.mymechanicagenda.helper.TakePictureHelper
import com.santosdaniel.mymechanicagenda.model.database.Document
import com.santosdaniel.mymechanicagenda.model.database.DocumentPhoto
import com.santosdaniel.mymechanicagenda.view.GenericStateFragment
import com.santosdaniel.mymechanicagenda.view.generic.ViewHelper
import com.santosdaniel.mymechanicagenda.view.vehicleDetails.VehicleDetailsModel
import java.io.File
import java.io.IOException


/**
 * Activity to present the contact with a list of vehicles
 */
class EditVehiclePictureFragment : GenericStateFragment<VehicleDetailsModel>() {


    /*UI variables*/

    /**
     * Reference to picture of the vehicle
     */
    private var vehiclePicture: ImageView? = null


    /**
     * Find the views that is going to use in the fragment
     *
     * @param fragmentView Reference to the view of the fragment
     */
    private fun bindViews(fragmentView: View) {
        this.vehiclePicture = fragmentView.findViewById(R.id.tool_bar_picture)
    }


    /**
     * Bind actions to the elements of the UI
     */
    private fun bindActions() {
        if (this.vehiclePicture != null && this.activity != null) {
            this.vehiclePicture!!.setOnClickListener {
                try {
                    val pictureFile = TakePictureHelper.createPictureFile(activity!!, VEHICLE_PICTURE_PREFIX)
                    addNewPicture(pictureFile)
                    TakePictureHelper.dispatchTakePictureIntent(activity!!, pictureFile)
                } catch (ex: IOException) {
                    Log.d("TakePicture", "Impossible to create the picture file", ex)
                }
            }
        }
    }

    /**
     * Add a new picture to the list of pictures handle by the fragment
     */
    private fun addNewPicture(pictureFile: File) {
        if ((super._state != null) && (super._state!!.vehicle != null)) {
            //Creates one new document, if needs it
            if (super._state!!.vehicle!!.photo == null) {
                val document = Document()
                document.photos = ArrayList()
                super._state!!.vehicle!!.photo = document
            }
            val document: Document = super._state!!.vehicle!!.photo!!
            val newPhoto = DocumentPhoto()
            newPhoto.document = document
            newPhoto.path = pictureFile.absolutePath
            document.photos!!.add(newPhoto)
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

        val fragmentView = inflater.inflate(R.layout.collapsing_image_toolbar, container, false)
        bindViews(fragmentView)
        bindActions()
        return fragmentView
    }


    /**
     * Get the path of the photo of the vehicle
     */
    private fun getPicturePath(state: VehicleDetailsModel): String? {
        return if ((state == null) ||
                (state.vehicle == null) ||
                (state.vehicle!!.photo == null) ||
                (state.vehicle!!.photo!!.photos == null) ||
                (state.vehicle!!.photo!!.photos!!.isEmpty())
                       )
            null
        else
            state.vehicle!!.photo!!.photos!!.first().path
    }

    /**
     * Load the image of the customer
     */
    private fun loadPicture(state: VehicleDetailsModel) {
        val photoPath = getPicturePath(state)
        ViewHelper.loadImageOrDefault(activity!!, photoPath, R.mipmap.photo_camera, vehiclePicture)
    }

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    override fun setState(state: VehicleDetailsModel) {
        super._state = state
    }


    /**
     * Called when information of the fragment needs to be saved
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState, VEHICLE_DATA_KEY)
    }

    /**
     * Called when the activity is created and the fragment is recreated
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.loadStateFromBundle(savedInstanceState, VEHICLE_DATA_KEY)
    }

    private fun deletePicture(documentPhoto: DocumentPhoto): Boolean {
        if(documentPhoto.path != null) {
            File(documentPhoto.path)
        }
        return true
    }

    /*
     * Delete pictures that are not need it anymore
     */
    private fun deletePictures(_state: VehicleDetailsModel?) {
        if ((_state == null) ||
                (_state.vehicle == null) ||
                (_state.vehicle!!.photo == null) ||
                (_state.vehicle!!.photo!!.photos == null) ||
                (_state.vehicle!!.photo!!.photos!!.isEmpty())
                ) {
            return
        } else {
            val vehicle = _state.vehicle!!
            val photos = vehicle.photo!!.photos!!
            photos.forEach {  }
        }
    }

    override fun onResume() {
        super.onResume()
        if (super._state != null) {
            deletePictures(super._state)
            loadPicture(super._state!!)
        }
    }

    /**
     * Called when the activity is called on the result of one certain action
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                TakePictureHelper.REQUEST_TAKE_PHOTO -> {
                    val extras = data!!.extras
                    val imageBitmap = extras!!.get(THUMBNAIL_DATA_KEY) as Bitmap
                    vehiclePicture?.setImageBitmap(imageBitmap)
                }
            }
        }
    }

    companion object {
        val TAG = "EditVehiclePicFragment"
        val VEHICLE_DATA_KEY = "vehicleData"
        val THUMBNAIL_DATA_KEY = "data"
        val VEHICLE_PICTURE_PREFIX = "vehicle"
    }
}
