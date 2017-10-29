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
    }
}
