package com.santosdaniel.mymechanicagenda.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date


/**
 *
 * Helper to take a picture
 */
object TakePictureHelper {
    const val REQUEST_TAKE_PHOTO = 1
    const val FILE_PROVIDER_AUTHORITY = "com.santosdaniel.mymechanicagenda.fileProvider"
    private const val PICTURES_DIRECTORY = "pictures"
    private const val PICTURES_SUFFIX = ".jpg"


    private var mCurrentPhotoPath: String? = null

    @Throws(IOException::class)
    fun createPictureFile(context: Context, prefix: String): File {
        // Create an image file name
        val now = Date()
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(now)
        val imageFileName = "${prefix}_${timeStamp}_"
        val picturesDir = File(context.filesDir, PICTURES_DIRECTORY)
        picturesDir.mkdirs()
        val image = File.createTempFile(
                imageFileName, /* prefix */
                PICTURES_SUFFIX, /* suffix */
                picturesDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        return image
    }


    fun dispatchTakePictureIntent(activity: Activity, photoFile: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {

            val photoURI = FileProvider.getUriForFile(activity,
                    FILE_PROVIDER_AUTHORITY,
                    photoFile)
            //Grant writing permissions to the camera
            val resInfoList = activity.packageManager.queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY)
            resInfoList
                    .asSequence()
                    .map { it.activityInfo.packageName }
                    .forEach { activity.grantUriPermission(it, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION) }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
    }
}
