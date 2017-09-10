package com.santosdaniel.mymechanicagenda.presenter.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Loads one image locally
 */
public class LocalImageLoader {

    private final Context context;
    private final ImageCache imageCache;

    /**
     * Constructs a new ImageLoader.
     *
     * @param imageCache The cache to use as an L1 cache.
     */
    public LocalImageLoader(@NonNull Context context, @NonNull ImageCache imageCache) {
        this.context = context;
        this.imageCache = imageCache;
    }

    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private void scaleImage(ImageView imageView) {

        Drawable drawing = imageView.getDrawable();
        if (drawing == null) {
        }
        Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpToPx(50);

        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        imageView.setImageDrawable(result);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView
                .getLayoutParams();
        params.width = width;
        params.height = height;
        imageView.setLayoutParams(params);

    }

    /**
     *
     *
     * @param imageUri
     * @param thumbnail
     */
    public void load(String imageUri, ImageView thumbnail) {
        Bitmap bitmap = imageCache.getBitmap(imageUri);
        if(bitmap == null) {
            try {
                Uri uri = Uri.parse(imageUri);
                bitmap = MediaStore.Images.Media.getBitmap(
                        context.getContentResolver(), uri);
                Log.d("Tag", bitmap.getWidth() + "");
                thumbnail.setImageBitmap(bitmap);
                scaleImage(thumbnail);
            } catch (Exception e) {
            }
        } else {
            thumbnail.setImageBitmap(bitmap);
        }
    }
}
