package com.santosdaniel.mymechanicagenda.helper

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog

import com.santosdaniel.mymechanicagenda.R


/**
 * Helper used to require permissions to the user in run time
 */
object PermissionsRequestHelper {

    /**
     * Show a dialog to the user why the permission is request
     *
     * @param activity              Context where is to create the dialog
     * @param title                 The title of the dialog to show to the user
     * @param message               Message that is to show to the user
     * @param permission            Permission that is necessary
     * @param permissionRequestCode the code of the permission required
     */
    private fun showExplanation(activity: Activity,
                                title: String,
                                message: String,
                                permission: String,
                                permissionRequestCode: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, id -> pRequestPermission(activity, permission, permissionRequestCode) }
        builder.create().show()
    }

    /**
     * Request the permission to the user
     *
     * @param activity              Context where is to create the dialog
     * @param permissionName        The name of the permission to request
     * @param permissionRequestCode The code that is going to be return to the activity when a user decides give or not permission
     */
    private fun pRequestPermission(activity: Activity, permissionName: String, permissionRequestCode: Int) =
            ActivityCompat.requestPermissions(activity,
                    arrayOf(permissionName), permissionRequestCode)

    /**
     * Checks if has permission to do something and if not requests to the user
     *
     * @param activity    Context where is to create the dialog
     * @param permission  The name of permission to request
     * @param requestCode Application specific request code to match with a result
     * @return False ->    Was not possible to the the demand permission
     * True ->     Everything went alright
     */
    fun requestPermission(activity: Activity, permission: String, requestCode: Int): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(activity, permission)

        return if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showExplanation(activity, activity.getString(R.string.permission_needed), activity.getString(R.string.reason), permission, requestCode)
            } else {
                pRequestPermission(activity, permission, requestCode)
            }
            false
        }
    }
}
