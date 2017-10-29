/**
 * Created by Salah
 */
package com.saos.salah.sketch.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;


/**
 * Entry point to manage Memory and Dish Cache
 */
public class CacheManager {

    private DiskCache diskCache;
    private MemoryCache memoryCache;

    public static String URL_KEY_INTENT = "URL_KEY";
    public static String BITMAP_KEY_INTENT = "BITMAP_KEY";

    private boolean isDiskCacheEnable = true;
    private Context context;

    /**
     * Initialize memoryCache and diskCache with default sizeCache
     * @param context
     */
    public CacheManager(Context context) {
        this.context = context;

        this.memoryCache = new MemoryCache(context);
        this.diskCache = new DiskCache(context);
    }


    /**
     * Initialize memoryCache with memorySize cache size
     * and dishCache with diskSize cache size
     * @param context
     * @param memorySize
     * @param diskSize
     */
    public CacheManager(Context context, int memorySize, int diskSize) {
        this.context = context;

        this.memoryCache = new MemoryCache(context, memorySize);
        this.diskCache = new DiskCache(context, diskSize);
    }

    /**
     * get bitmap link with url in MemoryCache otherwise in DiskCase
     * @param url
     * @return
     */
    public Bitmap getFromAllCache(String url) {
        Bitmap bitmap = this.memoryCache.get(url);
        if (bitmap == null)
            bitmap = this.diskCache.get(url);
        return bitmap;
    }

    /**
     * get bitmap link with url in MemoryCache
     * @param url
     * @return
     */
    public Bitmap getFromMemoryCache(String url) {
        return this.memoryCache.get(url);
    }

    /**
     * get bitmap link with url in DiskCache
     * @param url
     * @return
     */
    public Bitmap getFromDiskCache(String url) {
        if (isDiskCacheEnable)
            return this.diskCache.get(url);
        else
            return null;
    }

    /**
     * put in MemoryCache Bitmap link with url
     * @param url
     * @param bitmap
     */
    public void putToMemoryCache(String url, Bitmap bitmap) {
       this.memoryCache.put(url, bitmap);
    }

    /**
     * put to DiskCache Bitmap link with url
     * @param url
     * @param bitmap
     */
    public void putToDiskCache(String url, Bitmap bitmap) {
        if (isDiskCacheEnable)
            this.diskCache.put(url, bitmap);
    }

    /**
     * check if DiskCache contain a bitmap link with this url
     * @param url
     * @return
     */
    public boolean isDiskContainUrl(String url){
        if (isDiskCacheEnable)
            return this.diskCache.contain(url);
        else
            return false;
    }

    public void setIsDiskCacheEnable(boolean enable){
        isDiskCacheEnable = enable;
    }
}
