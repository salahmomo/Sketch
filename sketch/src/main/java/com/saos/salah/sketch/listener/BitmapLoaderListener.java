/**
 * Created by Salah
 */
package com.saos.salah.sketch.listener;

import android.graphics.Bitmap;


/**
 * Listener for Bitmap loading
 */
public interface BitmapLoaderListener {

    void onFinish(Bitmap bitmap);

    void onCancel();
}
