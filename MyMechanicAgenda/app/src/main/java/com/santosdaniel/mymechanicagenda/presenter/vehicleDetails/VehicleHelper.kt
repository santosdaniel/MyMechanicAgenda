package com.santosdaniel.mymechanicagenda.presenter.vehicleDetails

import android.content.Context
import com.santosdaniel.mymechanicagenda.helper.ContainerHelper
import com.santosdaniel.mymechanicagenda.helper.StringHelper
import com.santosdaniel.mymechanicagenda.model.database.*

/**
 * Helper to transform and validate information of the vehicle
 */
object VehicleHelper {
    /**
     *
     * @param vehicle   The vehicle for each is to get the brand and model
     * @param context   Context used to get the translation of the brand
     *
     * return A string with brand and model of the vehicle
     */
    fun brandAndModel(vehicle: Vehicle, context: Context): String {
        var sb = StringBuilder()
        // The brand
        if (vehicle.brand != null) {
            val brand: BrandEnum = vehicle.brand!!
            val brandStr = brand.brandName
            sb.append(brandStr)
        }
        // Space
        if ((vehicle.brand != null) && (vehicle.model != null)) {
            sb.append(StringHelper.SPACE)
        }
        //Mode of the vehicle
        if (vehicle.model != null) {
            val model = vehicle.model!!
            if (StringHelper.isNotNullOrEmpty(model.name)) {
                sb.append(model.name)
            }
        }
        return sb.toString()
    }

    /**
     * @vehicle the vehicle for each is to get the URI of the photo
     *
     * return the string to the path where the photo of the vehicle exists
     */
    fun photoUri(vehicle: Vehicle): String {
        return if (ContainerHelper.isEmpty(vehicle.documents)) {
            StringHelper.EMPTY_STRING
        } else {
            val documents: List<Document> = vehicle.documents!!
            val docPhoto: Document? = documents.firstOrNull { ((it.type != DocumentTypeEnum.PHOTO)) }
            if ((docPhoto == null) || (ContainerHelper.isEmpty(docPhoto.photos))) {
                StringHelper.EMPTY_STRING
            } else {
                val photos: List<DocumentPhoto> = (docPhoto!!).photos!!
                val photo: DocumentPhoto? = photos.firstOrNull()
                if ((photo == null) || (StringHelper.isNotNullOrEmpty(photo.path))) {
                    StringHelper.EMPTY_STRING
                } else {
                    photo.path!!
                }
            }
        }
    }
}
