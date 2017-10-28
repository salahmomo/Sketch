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
 * Created by Salah on 27/10/2017.
 */

public class NetworkClient {

    private final OkHttpClient client;

    public NetworkClient() {
        this.client = new OkHttpClient();
    }


    public Bitmap loadBitmap(final String url) {
        Log.i("NetworkClient", "loadBitmap");

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final Response response;
        try {
            response = client.newCall(request).execute();
            //BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inJustDecodeBounds = true;
            return BitmapFactory.decodeStream(response.body().byteStream()/*, null, options*/);
        } catch (IOException e) {
            Log.i("NetworkClient", "loadBitmap :" + e.getMessage());
        }

        return null;
    }

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
            return BitmapFactory.decodeStream(response.body().byteStream(), null, options);
        } catch (IOException e) {
            Log.e("NetworkClient", "loadBitmapForMeasure ERROR :" + e.getMessage());
        }

        return  null;
    }
}
