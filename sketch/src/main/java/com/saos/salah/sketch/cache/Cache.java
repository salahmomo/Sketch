package com.saos.salah.sketch.cache;

import android.graphics.Bitmap;

/**
 * Created by Salah on 29/10/2017.
 */

public interface Cache {
    Bitmap get(String url);
    void put(String url, Bitmap bitmap);

    boolean contain(String url);

}
