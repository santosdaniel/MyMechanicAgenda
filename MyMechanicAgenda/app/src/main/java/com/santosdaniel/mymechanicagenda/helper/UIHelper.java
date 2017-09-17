package com.santosdaniel.mymechanicagenda.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import java.lang.ref.WeakReference;


/**
 * Helper to access to the elements to put in the views
 */
public class UIHelper {


    /**
     * @param context    Context that is calling this method
     * @param drawableId The identifier of the drawable to get
     * @return Drawable that has the identifier passed (If possible)
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(Context context, int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(drawableId);
        } else {
            return context.getResources().getDrawable(drawableId);
        }
    }

    /**
     * Set the visibility parameter of a set of views
     *
     * @param visibility The visibility to set
     * @param views      The array of views to set the visibility
     */
    public static void setVisibility(int visibility, View... views) {
        if (ContainerHelper.isNotEmpty(views)) {
            for (View view : views) {
                view.setVisibility(visibility);
            }
        }
    }

    /**
     * Set the visibility parameter of a set of weak references to views
     *
     * @param visibility The visibility to set
     * @param refViews   The array of views to set the visibility
     */
    public static void setVisibility(int visibility, WeakReference<View>... refViews) {
        if (ContainerHelper.isNotEmpty(refViews)) {
            for (WeakReference<View> refView : refViews) {
                try {
                    View view = refView.get();
                    if (view != null) {
                        view.setVisibility(visibility);
                    }
                } catch (Exception e) {

                }
            }
        }
    }
}
