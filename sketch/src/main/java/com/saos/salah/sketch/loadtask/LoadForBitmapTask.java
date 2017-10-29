/**
 * Created by Salah
 */
package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;

import com.saos.salah.sketch.cache.CacheManager;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.BitmapLoaderListener;
import com.saos.salah.sketch.listener.LoadTaskListener;


/**
 * Load Task for BitmapLoading
 */
public class LoadForBitmapTask extends LoadTask {

    private BitmapLoaderListener bitmapLoaderListener;

    public LoadForBitmapTask(LoadTaskListener loadTaskListener, BitmapLoaderListener bitmapLoaderListener, CacheManager cacheManager, NetworkClient networkClient) {
        super(loadTaskListener, cacheManager, networkClient);
        this.bitmapLoaderListener = bitmapLoaderListener;
    }

    /**
     *  call BitmapListenr on Finish and end task
     * @param result
     */
    @Override
    protected void onPostExecute(Bitmap result) {
        bitmapLoaderListener.onFinish(result);
        loadTaskListener.onFinishTask();
    }

    protected void onCancelled() {
        super.onCancelled();
        bitmapLoaderListener.onCancel();
    }
}
