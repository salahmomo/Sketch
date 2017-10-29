/**
 * Created by Salah
 */
package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.saos.salah.sketch.cache.CacheManager;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.LoadTaskListener;

/**
 * Actions to load a ImageView
 */
public abstract class LoadTask extends AsyncTask<String, Void, Bitmap> {

    protected CacheManager cacheManager;
    protected NetworkClient networkClient;
    protected LoadTaskListener loadTaskListener;

    public LoadTask(LoadTaskListener loadTaskListener, CacheManager cacheManager, NetworkClient networkClient) {
        this.loadTaskListener = loadTaskListener;
        this.cacheManager = cacheManager;
        this.networkClient = networkClient;
    }

    /**
     * Get a bitmap image from cache or network
     *      get image from MemoryCache, if yes return it otherwise
     *      get from DiskCache, if yes save in MemoryCache and return it otherwise
     *      get from network, save in MemoryCache and DiskCache and return it
     * @param urls
     * @return
     */
    @Override
    protected Bitmap doInBackground(String... urls) {
        Log.i("LoadTask", "start doInBackground");
        String url = urls[0];
        Bitmap imageResult = null;

        if ((imageResult = this.cacheManager.getFromMemoryCache(url)) != null) {
            Log.i("LoadTask", "Get From Cache");
            return imageResult;
        }
        else if (this.cacheManager.isDiskContainUrl(url)) {
            imageResult =  this.cacheManager.getFromDiskCache(url);

            cacheManager.putToMemoryCache(url, imageResult);

            Log.i("LoadTask", "doInBackground - Get From Disk Cache");

            return imageResult;
        }
        else {
            Log.i("LoadTask", "doInBackground - Start Using Network client");
            imageResult =   this.networkClient.loadBitmap(url);

            if (imageResult != null) {
                Log.i("LoadTask", "doInBackground - Get From Network");

                cacheManager.putToMemoryCache(url, imageResult);
                cacheManager.putToDiskCache(url, imageResult);
                return imageResult;
            }
        }
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        loadTaskListener.onFinishTask();
    }
}
