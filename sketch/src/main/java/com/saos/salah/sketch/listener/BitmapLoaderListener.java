package com.saos.salah.sketch.listener;

import android.graphics.Bitmap;

/**
 * Created by Salah on 28/10/2017.
 */

public interface BitmapLoaderListener {

    void onFinish(Bitmap bitmap);

    void onCancel();
}
