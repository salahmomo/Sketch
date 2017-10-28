package com.saos.salah.sketch.loadtask;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.saos.salah.sketch.Sketch;

/**
 * Created by Salah on 27/10/2017.
 */

public class LoadForImageViewTask extends LoadTask {
    private ImageView imageView;

    public LoadForImageViewTask(ImageView image, LoadTaskManager.LoadTaskListener loadTaskListener) {
        super(loadTaskListener);
        this.imageView = image;
    }

    protected void onPostExecute(Bitmap result) {
        this.imageView.setImageBitmap(result);
        loadTaskListener.onFinishTask();
    }
}
