/**
 * Created by Salah
 */
package com.saos.salah.sketch;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.saos.salah.sketch.cache.CacheManager;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.BitmapLoaderListener;
import com.saos.salah.sketch.loadtask.LoadTaskManager;


/**
 * Skecth Lib entry point
 */
public class Sketch {
    private static Sketch instance = null;

    private NetworkClient clientManager;
    private CacheManager cacheManager;
    private LoadTaskManager loadTaskManager;

    private Sketch (Context context) {
        this.loadTaskManager = new LoadTaskManager();
        this.clientManager = new NetworkClient();
        this.cacheManager = new CacheManager(context);
    }

    /**
     * Initialize Sketch with Context context memorySize memory cache and diskSize disk cache
     * @param context
     * @param memorySize
     * @param diskSize
     */
    private Sketch (Context context, int memorySize, int diskSize) {
        this.loadTaskManager = new LoadTaskManager();
        this.clientManager = new NetworkClient();
        this.cacheManager = new CacheManager(context, memorySize, diskSize);
    }

    public static Sketch getInstance(Context context) {
        if  (instance == null) {
            instance = new Sketch(context);
        }

        return instance;
    }

    public static Sketch getInstance(Context context, int memorySize, int diskSize) {
        if  (instance == null) {
            instance = new Sketch(context, memorySize, diskSize);
        }

        return instance;
    }

    public Sketch enableDiskCache() {
        cacheManager.setIsDiskCacheEnable(true);

        return instance;
    }

    public Sketch disableDiskCache() {
        cacheManager.setIsDiskCacheEnable(false);

        return instance;
    }

    /**
     * Load Image from String url with listener BitmapLoaderListener
     * @param url
     * @param bitmapLoaderListener
     */
    public void loadImage(final String url, final BitmapLoaderListener bitmapLoaderListener) {
        this.loadTaskManager.loadImageIntoBitmap(url, bitmapLoaderListener, cacheManager, clientManager);
    }

    /**
     * Load Image from String url into ImageView imageView
     * @param url
     * @param imageView
     */
    public void loadImageIntoImageView(final String url, final ImageView imageView){
        this.loadTaskManager.loadImageIntoImageView(url, imageView, cacheManager, clientManager);
    }

    /**
     * Load Image from String url into ImageView imageView with width & height size
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    /*public void loadImageIntoImageViewWithSize(String url, ImageView imageView, int width, int height){
        this.loadTaskManager.loadImageIntoImageView(url, imageView, cacheManager, clientManager);
    }*/
}
