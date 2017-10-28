package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;

import com.saos.salah.sketch.listener.BitmapLoaderListener;

/**
 * Created by Salah on 27/10/2017.
 */

public class LoadForBitmapTask extends LoadTask {

    private BitmapLoaderListener bitmapLoaderListener;

    public LoadForBitmapTask(LoadTaskManager.LoadTaskListener loadTaskListener, BitmapLoaderListener bitmapLoaderListener) {
        super(loadTaskListener);
        this.bitmapLoaderListener = bitmapLoaderListener;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        loadTaskListener.onFinishTask();
        bitmapLoaderListener.onFinish(result);
    }

    protected void onCancelled() {
        super.onCancelled();
        bitmapLoaderListener.onCancel();
    }
}
