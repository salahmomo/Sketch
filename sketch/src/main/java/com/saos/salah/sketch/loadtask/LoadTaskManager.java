/**
 * Created by Salah
 */
package com.saos.salah.sketch.loadtask;

import android.util.Log;
import android.widget.ImageView;

import com.saos.salah.sketch.cache.CacheManager;
import com.saos.salah.sketch.client.NetworkClient;
import com.saos.salah.sketch.listener.BitmapLoaderListener;
import com.saos.salah.sketch.listener.LoadTaskListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage all LaodTask
 */
public class LoadTaskManager {

    //contains all LoadTask link by ImageView
    private Map<ImageView, LoadTask> imageTaskDictionary;
    //contains all LoadTask link by url
    private Map<String, LoadTask> urlTaskDictionary;

    public LoadTaskManager() {
        this.imageTaskDictionary = new HashMap<>();
        this.urlTaskDictionary = new HashMap<>();
    }

    /**
     * check if a LoadTask use a ImageView and start task to loading the image from url into the image view
     * @param url
     * @param imageView
     * @param cacheManager
     * @param networkClient
     */
    public void loadImageIntoImageView(final String url, final ImageView imageView, CacheManager cacheManager, NetworkClient networkClient) {
            cancelLoadImageForImageView(imageView);

            LoadTask loadTask = new LoadForImageViewTask(imageView, initImageLoadTaskListener(url, imageView), cacheManager, networkClient);
            imageTaskDictionary.put(imageView, loadTask);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }

    /**
     * check if a LoadTask use a ImageView and start task to loading the image of size width & height from url into the image view
     * @param url
     * @param imageView
     * @param width
     * @param height
     * @param cacheManager
     * @param networkClient
     */
    /*public void loadImageIntoImageViewWithSize(String url, ImageView imageView, int width, int height, CacheManager cacheManager, NetworkClient networkClient) {
            cancelLoadImageForImageView(imageView);

            LoadTask loadTask = new LoadForImageViewTask(imageView, initImageLoadTaskListener(url, imageView), cacheManager, networkClient);
            imageTaskDictionary.put(imageView, loadTask);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }*/

    /**
     * start task to loading a bitmap from url
     * @param url
     * @param bitmapLoaderListener
     * @param cacheManager
     * @param networkClient
     */
    public void loadImageIntoBitmap(String url, BitmapLoaderListener bitmapLoaderListener, CacheManager cacheManager, NetworkClient networkClient) {
            LoadTask loadTask = new LoadForBitmapTask(initBitmapLoadTaskListener(url),  bitmapLoaderListener, cacheManager, networkClient);
            urlTaskDictionary.put(url, loadTask);
            loadTask.execute(url);
    }

    /**
     * cancel a LoadTask who load from url
     * @param url
     */
    public void cancelLoadImageForUrl(String url) {
        if (urlTaskDictionary.containsKey(url))
            urlTaskDictionary.get(url).cancel(true);
    }

    /**
     * check if a LoadTask loading with url
     * @param url
     * @return
     */
    public boolean isloadingOnCurrentUrl(String url) {
        return urlTaskDictionary.containsKey(url);
    }

    /**
     * cancel a LoadTask loading who use ImageView imageView
     * @param imageView
     */
    public void cancelLoadImageForImageView(ImageView imageView) {
        if (imageTaskDictionary.containsKey(imageView)) {
            Log.i("LoadTaskManager", "cancelLoadImageForImageView isCancel");
            imageTaskDictionary.get(imageView).cancel(true);
            imageView.setImageBitmap(null);
        }
    }

    /**
     * Initialize LoadTaskListener  for the end of LoadForImageViewTask
     *      remove from dictionary the LoadTask using ImageView imageView and String url
     * @param url
     * @param imageView
     * @return
     */
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

    /**
     * Initialize LoadTaskListener  for the end of LoadForBitmapTask
     *      remove from dictionary the LoadTask using String url
     * @param url
     * @return
     */
    private LoadTaskListener initBitmapLoadTaskListener(final String url) {
        LoadTaskListener loadTaskListener = new LoadTaskListener() {
            @Override
            public void onFinishTask() {
                urlTaskDictionary.remove(url);
            }
        };

        return loadTaskListener;
    }
}
