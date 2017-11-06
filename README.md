# Sketch
 Library for loading, display and caching image on Android

## Features
* Async multithread image loading on imageView or only in cache
* Image disk & memory caching

## Usage


### Loading image into ImageView
``` java
Sketch.getInstance(context).loadImageIntoImageView(url, image);
```

### Manage caching
#### Initialize custom memory and disk caching

``` java
Sketch.getInstance(context, memorySize, dislSize).loadImageIntoImageView(url, image);
```

#### Enable/disable disk caching

* Disable disk caching

``` java
Sketch.getInstance(context).disableDiskCache().loadImageIntoImageView(url, image);
```

* Enable disk caching

``` java
Sketch.getInstance(context).enableDiskCache().loadImageIntoImageView(url, image);
```


### Simple loading Image in cache with listener

``` java
Sketch.getInstance(context).loadImage(url, new BitmapLoaderListener() {
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
