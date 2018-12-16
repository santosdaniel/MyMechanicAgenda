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
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.helper.TakePictureHelper
import com.santosdaniel.mymechanicagenda.model.database.Document
import com.santosdaniel.mymechanicagenda.model.database.DocumentPhoto
import com.santosdaniel.mymechanicagenda.model.database.DocumentTypeEnum
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
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
     * Create a document for putting the photo
     */
    private fun createPhotoDocument(): Document {
        val document = Document(DocumentTypeEnum.PHOTO)
        document.photos = ArrayList()
        return document
    }

    /**
     * Add a new picture to the list of pictures handle by the fragment
     */
    private fun addNewPicture(pictureFile: File) {
        if ((super.lState != null) && (super.lState!!.vehicle != null)) {
            val vehicle: Vehicle = super.lState!!.vehicle!!

            //Creates one new document, if needs it
            var photo: Document? = null
            if (vehicle.documents == null) {
                vehicle.documents = ArrayList()
                photo = createPhotoDocument()
                vehicle.documents!!.add(photo)
            } else {
                photo = vehicle.documents!!.find { document -> document.type == DocumentTypeEnum.PHOTO }
                if (photo == null) {
                    photo = createPhotoDocument()
                    vehicle.documents!!.add(photo)
                }
            }
            val newPhoto = DocumentPhoto()
            newPhoto.document_id = photo!!.id
            newPhoto.path = pictureFile.absolutePath
            photo.photos!!.add(newPhoto)
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
                ContainerHelper.isEmpty(state.vehicle!!.documents))
            null
        else {
            val photoDocument = state.vehicle!!.documents!!.find { document -> document.type == DocumentTypeEnum.PHOTO }
            if (photoDocument == null) {
                null
            } else {
                if(ContainerHelper.isEmpty(photoDocument.photos)) {
                    null
                } else {
                    photoDocument.photos!!.first().path
                }
            }
        }
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
        super.lState = state
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


    override fun onResume() {
        super.onResume()
        if (super.lState != null) {
            loadPicture(super.lState!!)
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
        const val TAG = "EditVehiclePicFragment"
        const val VEHICLE_DATA_KEY = "vehicleData"
        const val THUMBNAIL_DATA_KEY = "data"
        const val VEHICLE_PICTURE_PREFIX = "vehicle"
    }
}
