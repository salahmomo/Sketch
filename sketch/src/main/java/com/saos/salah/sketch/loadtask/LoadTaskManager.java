package com.saos.salah.sketch.loadtask;

import android.util.Log;
import android.widget.ImageView;

import com.saos.salah.sketch.listener.BitmapLoaderListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Salah on 27/10/2017.
 */

public class LoadTaskManager {

    private Map<ImageView, LoadTask> imageTaskDictionary;
    private Map<String, LoadTask> urlTaskDictionary;

    public LoadTaskManager() {
        this.imageTaskDictionary = new HashMap<>();
        this.urlTaskDictionary = new HashMap<>();
    }

    public void loadImageIntoImageView(final String url, final ImageView imageView) {
            cancelLoadImageForImageView(imageView);

            LoadTask loadTask = new LoadForImageViewTask(imageView, initImageLoadTaskListener(url, imageView));
            imageTaskDictionary.put(imageView, loadTask);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }

    public void loadImageIntoImageViewWithSize(String url, ImageView imageView, int width, int height) {
            cancelLoadImageForImageView(imageView);

            LoadTask loadTask = new LoadForImageViewTask(imageView, initImageLoadTaskListener(url, imageView));
            imageTaskDictionary.put(imageView, loadTask);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }


    public void loadImageIntoBitmap(String url, BitmapLoaderListener bitmapLoaderListener) {
            LoadTask loadTask = new LoadForBitmapTask(initBitmapLoadTaskListener(url),  bitmapLoaderListener);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }

    public void cancelLoadImageForUrl(String url) {
        if (urlTaskDictionary.containsKey(url))
            urlTaskDictionary.get(url).cancel(true);
    }

    public boolean isloadingOnCurrentUrl(String url) {
        return urlTaskDictionary.containsKey(url);
    }

    public void cancelLoadImageForImageView(ImageView imageView) {
        if (imageTaskDictionary.containsKey(imageView)) {
            Log.i("LoadTaskManager", "cancelLoadImageForImageView isCancel");
            imageTaskDictionary.get(imageView).cancel(true);
            imageView.setImageBitmap(null);
        }
    }

    private LoadTaskListener initImageLoadTaskListener(final String url, final ImageView imageView) {
        LoadTaskListener loadTaskListener = new LoadTaskListener() {
            @Override
            public void onFinishTask() {
                imageTaskDictionary.remove(imageView);
                urlTaskDictionary.remove(url);
            }
        };

        return loadTaskListener;
    }

    private LoadTaskListener initBitmapLoadTaskListener(final String url) {
        LoadTaskListener loadTaskListener = new LoadTaskListener() {
            @Override
            public void onFinishTask() {
                urlTaskDictionary.remove(url);
            }
        };

        return loadTaskListener;
    }

    public interface LoadTaskListener {
        void onFinishTask();
    }
}
