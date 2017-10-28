package com.saos.salah.sketchy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainActivity extends AppCompatActivity {

    RecyclerView listView;
    ImagesCellAdapter imagesCellAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ImageLoader imageLoader;
    public static DisplayImageOptions displayImageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (RecyclerView) findViewById(R.id.list_item);
        listView.setHasFixedSize(true);

        imagesCellAdapter = new ImagesCellAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        imagesCellAdapter = new ImagesCellAdapter(getBaseContext());
        listView.setAdapter(imagesCellAdapter);

        displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageLoader = ImageLoader.getInstance();
                ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(MainActivity.this)
                        .memoryCacheSize(2 * 1024 * 1024)
                        .memoryCacheSizePercentage(13) // default
                        .build();
                imageLoader.init(configuration);
            }
        }).start();

        /*for (int i= 0 ; i < 100000; i++) {
            Sketch.getInstance().initCacheWithDefaultSize(this).loadImage("https://vignette4.wikia.nocookie.net/finalfantasy/images/6/60/FF4TheAfterIllustration2.jpg/revision/latest?cb=20120707013849", new BitmapLoaderListener() {
                @Override
                public void onFinish(Bitmap bitmap) {

                }

                @Override
                public void onCancel() {

                }
            });
            //Picasso.with(this).load("https://vignette4.wikia.nocookie.net/finalfantasy/images/6/60/FF4TheAfterIllustration2.jpg/revision/latest?cb=20120707013849");
        }*/
        /*(new DownloadImageTask(imageView)).execute("https://vignette.wikia.nocookie.net/finalfantasy/images/2/24/PFF_Sketch.png/revision/latest/top-crop/width/480/height/480?cb=20170812203632");
        (new DownloadImageTask(imageView)).execute("https://vignette.wikia.nocookie.net/finalfantasy/images/2/24/PFF_Sketch.png/revision/latest/top-crop/width/480/height/480?cb=20170812203632");
        (new DownloadImageTask(imageView)).execute("https://vignette.wikia.nocookie.net/finalfantasy/images/2/24/PFF_Sketch.png/revision/latest/top-crop/width/480/height/480?cb=20170812203632");
        (new DownloadImageTask(imageView)).execute("https://vignette.wikia.nocookie.net/finalfantasy/images/2/24/PFF_Sketch.png/revision/latest/top-crop/width/480/height/480?cb=20170812203632");
        (new DownloadImageTask(imageView)).execute("https://vignette.wikia.nocookie.net/finalfantasy/images/2/24/PFF_Sketch.png/revision/latest/top-crop/width/480/height/480?cb=20170812203632");*/
    }
}
