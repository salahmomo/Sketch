/**
 * Created by Salah
 */

package com.saos.salah.sketch.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Manage all operation on memory cache
 */

public class MemoryCache implements Cache{

    public static int CACHE_INITIALIZED = 10;
    public static int NOT_ENOUGHT_MEMORY = 11;

    private LruCache<String, Bitmap> bitmapCache;
    private int cacheSize;


    private Context context;


    public MemoryCache(Context context){
        this.context = context;
        initializedWithDefaultCacheSize();
    }

    public MemoryCache(Context context, int size){
        this.context = context;
        initializedWithCacheSize(size);
    }


    private int initializedWithCacheSize(int cacheSize) {
        Log.i("CacheManager", "initializedWithCacheSize");
        return createLruCacheWithCacheSize(cacheSize);
    }

    public int initializedWithDefaultCacheSize() {
        Log.i("CacheManager", "initializedWithDefaultCacheSize");
        int tmpCacheSize = getDefaultCacheSize();

        return createLruCacheWithCacheSize(tmpCacheSize);
    }

    private int createLruCacheWithCacheSize(int cacheSize) {
        if (isEnoughtCacheSize(cacheSize)) {
            Log.i("CacheManager", "cacheInitialize with " + cacheSize);
            this.cacheSize = cacheSize;
            bitmapCache = new LruCache<>(cacheSize);
            return CACHE_INITIALIZED;
        }
        else {
            Log.i("CacheManager", "cache not Initialize with " + cacheSize);
            return NOT_ENOUGHT_MEMORY;
        }
    }

    public void put(String url, Bitmap image) {
        Log.i("CacheManager", "putBitmapOnCache");
        synchronized (this) {
            bitmapCache.put(url, image);
        }
    }

    public boolean contain(String url) {
        synchronized (this) {
            return !(bitmapCache.get(url) == null);
        }
    }

    public Bitmap get(String url){
        Log.i("CacheManager", "getBitmapFromCache");
        synchronized (this) {
            return bitmapCache.get(url);
        }
    }

    private int getDefaultCacheSize() {
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int tmpCacheSize = 1024 * 1024 * memClass / 16;
        return tmpCacheSize;
    }

    private boolean isEnoughtCacheSize(int cacheSize) {
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

        return !(cacheSize >= 1024 * 1024 * memClass);
    }
}
