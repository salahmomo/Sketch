/**
 * Created by Salah
 */
package com.saos.salah.sketch.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.saos.salah.sketch.Utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Manage load with client
 */
public class NetworkClient {

    private final OkHttpClient client;

    public NetworkClient() {
        this.client = new OkHttpClient();
    }


    /**
     * load a simple bitmap from url
     * @param url
     * @return
     */
    public Bitmap loadBitmap(final String url) {
        Log.i("NetworkClient", "loadBitmap");

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Response response;
        try {
            response = client.newCall(request).execute();
            return BitmapFactory.decodeStream(response.body().byteStream());
        } catch (IOException e) {
            Log.i("NetworkClient", "loadBitmap :" + e.getMessage());
        }

        return null;
    }

    /**
     * Load a bitmap with a simple size give by width and height
     * @param url
     * @param width
     * @param height
     * @return
     */
    public Bitmap loadBitmapForMeasure(final String url, final int width, final int height) {
        Log.i("NetworkClient", "loadBitmapForMeasure");
        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Response response;
        try {
            response = client.newCall(request).execute();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = Utils.calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            Bitmap result = BitmapFactory.decodeStream(response.body().byteStream(), null, options);
            response.close();
            return result;
        } catch (IOException e) {
            Log.e("NetworkClient", "loadBitmapForMeasure ERROR :" + e.getMessage());
        }

        return  null;
    }
}
