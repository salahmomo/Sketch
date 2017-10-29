/**
 * Created by Salah
 */
package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.saos.salah.sketch.cache.CacheManager;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.LoadTaskListener;

import java.lang.ref.WeakReference;

/**
 * LoadTask for Bitmap Loading into ImageView
 */
public class LoadForImageViewTask extends LoadTask {
    private WeakReference<ImageView> imageView;

    public LoadForImageViewTask(ImageView image, LoadTaskListener loadTaskListener, CacheManager cacheManager, NetworkClient networkClient) {
        super(loadTaskListener, cacheManager, networkClient);
        this.imageView = new WeakReference<ImageView>(image);
    }

    /**
     * Set bitmap into ImageView and end Task
     * @param result
     */
    protected void onPostExecute(final Bitmap result) {
        Log.i("LoadForImageViewTask", "onPostExecute");
        this.imageView.get().setImageBitmap(result);
        this.imageView.get().postInvalidate();
        loadTaskListener.onFinishTask();
    }
}
