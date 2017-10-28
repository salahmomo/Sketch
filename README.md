# Sketch
An lazy image loading library for Android

## Features
* Async multithread image loading on imageView or only in cache
* Image caching in memory

## Usage


### Loading image into ImageView
``` java
Sketch.getInstance().initCacheWithDefaultSize(context).loadImageIntoImageView(urls.get(position), holder.image);
```

### Simple loading Image in cache with listener

``` java
Sketch.getInstance().initCacheWithDefaultSize(context).loadImage(urls.get(position), new BitmapLoaderListener() {
            @Override
            public void onFinish(Bitmap bitmap) {
            // do action
            }

            @Override
            public void onCancel() {
            // do action
            }
        });
```
