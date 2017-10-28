package com.saos.salah.sketch;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.saos.salah.sketch.cache.DiskCache;
import com.saos.salah.sketch.cache.MemoryCache;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.BitmapLoaderListener;
import com.saos.salah.sketch.loadtask.LoadTaskManager;

/**
 * Created by Salah on 27/10/2017.
 */

public class Sketch {
    private static Sketch instance = null;

    private static MemoryCache cacheManager;
    private static NetworkClient clientManager;
    private static DiskCache diskCache;

    private static LoadTaskManager loadTaskManager;

    private Sketch () {
    }

    public static Sketch getInstance() {
        if  (instance == null) {
            instance = new Sketch();
            loadTaskManager = new LoadTaskManager();
            clientManager = new NetworkClient();
        }

        return instance;
    }

    public Sketch initCacheWithSize(Context context, int sizeCache) {
        if (cacheManager == null)
            cacheManager = new MemoryCache(context, sizeCache);

        initDiskCache(context);
        return getInstance();
    }

    public Sketch initCacheWithDefaultSize(Context context) {
        if (cacheManager == null)
            cacheManager = new MemoryCache(context);

        initDiskCache(context);
        return getInstance();
    }

    public Bitmap getFromCache(String url) {
        Log.i("Sketch", "getFromCache");
        Bitmap bitmap = cacheManager.getBitmapFromCache(url);
        return bitmap;
    }

    public void putToCache(String url, Bitmap bitmap) {
        Log.i("Sketch", "putToCache");
        cacheManager.putBitmapOnCache(url, bitmap);
    }

    private void initDiskCache(Context context) {
        if (diskCache == null)
            diskCache = new DiskCache(context);
    }

    public void putToDiskCache(String url, Bitmap bitmap) {
        diskCache.putBitmap(url, bitmap);
    }

    public Bitmap getToDiskCache(String url) {
        return diskCache.getBitmap(url);
    }


    public Bitmap loadBitmapFromClient(String url) {
        Log.i("Sketch", "loadBitmapFromClient");
        return clientManager.loadBitmap(url);
    }

    public Bitmap loadBitmapFromClientWithSize(String url, int width, int height) {
        Log.i("Sketch", "loadBitmapFromClient");
        return clientManager.loadBitmapForMeasure(url, width, height);
    }

    public void loadImage(final String url, final BitmapLoaderListener bitmapLoaderListener) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
              loadTaskManager.loadImageIntoBitmap(url, bitmapLoaderListener);
            }
        })).run();
    }

    public Sketch loadImageIntoImageView(final String url, final ImageView imageView){
        (new Thread(new Runnable() {
            @Override
            public void run() {
                loadTaskManager.loadImageIntoImageView(url, imageView);
            }
        })).run();

        return getInstance();
    }

    public Sketch loadImageIntoImageViewWithSize(String url, ImageView imageView, int width, int height){
        loadTaskManager.loadImageIntoImageView(url, imageView);

        return getInstance();
    }
}
