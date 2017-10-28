package com.saos.salah.sketch.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;


/**
 * Created by Salah on 28/10/2017.
 */

public class DiskCache {
    private DiskLruCache mDiskLruCache;
    private final Object mDiskCacheLock = new Object();
    private boolean mDiskCacheStarting = true;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final String DISK_CACHE_SUBDIR = "thumbnails";

    public DiskCache(Context context){
        File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE);
            mDiskCacheStarting = false; // Finished initialization
            mDiskCacheLock.notifyAll(); // Wake any waiting threads
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath = context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    public void putBitmap(String url) {
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskLruCache.edit(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapFromDiskCache(String key) {
        synchronized (mDiskCacheLock) {
            // Wait while disk cache is started from background thread
            while (mDiskCacheStarting) {
                try {
                    mDiskCacheLock.wait();
                } catch (InterruptedException e) {}
            }
            if (mDiskLruCache != null) {
//                return mDiskLruCache.get(key);
            }
        }
        return null;
    }
}
