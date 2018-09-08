package com.santosdaniel.mymechanicagenda.helper

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v4.app.Fragment

import com.santosdaniel.mymechanicagenda.R
import java.lang.ref.WeakReference


/**
 * Helper used to require permissions to the user in run time
 */
object PermissionsRequestHelper {

    /**
     * Show a dialog to the user why the permission is request
     *
     * @param refActivity              Context where is to create the dialog
     * @param title                 The title of the dialog to show to the user
     * @param message               Message that is to show to the user
     * @param permission            Permission that is necessary
     * @param permissionRequestCode the code of the permission required
     */
    private fun showExplanation(fragment: Fragment,
                                title: String,
                                message: String,
                                permission: String,
                                permissionRequestCode: Int) {
        val builder = AlertDialog.Builder(fragment.context!!)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, id -> pRequestPermission(fragment, permission, permissionRequestCode) }
        builder.create().show()
    }

    /**
     * Request the permission to the user
     *
     * @param refActivity           Reference to the fragment where needs the permission
     * @param permissionName        The name of the permission to request
     * @param permissionRequestCode The code that is going to be return to the activity when a user decides give or not permission
     */
    private fun pRequestPermission(fragment: Fragment,
                                   permissionName: String, permissionRequestCode: Int) =
            fragment.requestPermissions(arrayOf(permissionName), permissionRequestCode)

    /**
     * Checks if has permission to do something and if not requests to the user
     *
     * @param refActivity    Context where is to create the dialog
     * @param permission  The name of permission to request
     * @param requestCode Application specific request code to match with a result
     * @return False ->    Was not possible to the the demand permission
     * True ->     Everything went alright
     */
    fun requestPermission(refActivity: WeakReference<FragmentActivity>, fragment: Fragment, permission: String, requestCode: Int): Boolean {
        val context = refActivity.get()!!.applicationContext
        val permissionCheck = ContextCompat.checkSelfPermission(context, permission)

        return if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(refActivity.get()!!, permission)) {
                val title = context.getString(R.string.permission_needed)
                val description = context.getString(R.string.reason)
                showExplanation(fragment, title, description, permission, requestCode)
            } else {
                pRequestPermission(fragment, permission, requestCode)
            }
            false
        }
    }
}
