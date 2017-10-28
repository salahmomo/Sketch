package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.saos.salah.sketch.Sketch;

/**
 * Created by Salah on 27/10/2017.
 */

public abstract class LoadTask extends AsyncTask<String, Void, Bitmap> {

    protected LoadTaskManager.LoadTaskListener loadTaskListener;


    public LoadTask(LoadTaskManager.LoadTaskListener loadTaskListener) {
        this.loadTaskListener = loadTaskListener;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap imageResult = null;

        Log.i("LoadTask", "start doInBackground");
        if ((imageResult = getImageFromCache(url)) != null) {
            Log.i("LoadTask", "Get From Cache YES");
            return imageResult;
        }
        else {
            Log.i("LoadTask", "doInBackground Start Using client");
            imageResult =  Sketch.getInstance().loadBitmapFromClientWithSize(url, 100, 100);

            if (imageResult != null) {
                Log.i("LoadTask", "doInBackground Image Find");
                Sketch.getInstance().putToCache(url, imageResult);
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

    protected Bitmap getImageFromCache(String url) {
        Bitmap bitmap = Sketch.getInstance().getFromCache(url);
        return bitmap;
    }

}
