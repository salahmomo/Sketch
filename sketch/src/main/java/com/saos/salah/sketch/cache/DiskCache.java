/**
 * Created by Salah
 */


package com.saos.salah.sketch.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Disk cache
 */

public class DiskCache {
    private static String TAG = DiskCache.class.getName();
    private DiskLruCache mDiskLruCache;

    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.PNG;
    private int mCompressQuality = 70;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final String DISK_CACHE_SUBDIR = "thumbnails";

    public DiskCache(Context context){
        try {
            File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
            mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * Put Bitmap image int DiskCache with url for key
     * @param url
     * @param bitmap
     */
    public void putBitmap(String url, Bitmap bitmap) {
        synchronized (this) {
            url = crateBase64String(url);
            DiskLruCache.Editor editor = null;
            try {
                editor = mDiskLruCache.edit(url);
                if (editor == null)
                    return;

                if (writeBitmapToFile(bitmap, editor)) {
                    mDiskLruCache.flush();
                    editor.commit();
                    Log.i(TAG, "Put bitmap into Disk cache");
                } else {
                    editor.abort();
                }
            } catch (IOException e) {
                Log.e(TAG, "Error msg : " + e.getMessage());
                try {
                    if (editor != null) {
                        editor.abort();
                    }
                } catch (IOException ignored) {
                }
            }
        }
    }


    /**
     * get Bitmap image from DiskCache with url for key
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url) {
        synchronized (this) {
            url = crateBase64String(url);
            Bitmap bitmap = null;
            DiskLruCache.Snapshot snapshot = null;
            try {
                snapshot = mDiskLruCache.get(url);
                if (snapshot == null)
                    return null;

                final InputStream in = snapshot.getInputStream(0);
                if (in != null) {
                    final BufferedInputStream buffIn = new BufferedInputStream(in);
                    bitmap = BitmapFactory.decodeStream(buffIn);
                    Log.i(TAG, "get bitmap From Disk cache done");
                }
            } catch (IOException e) {
                Log.e(TAG, "Error msg : " + e.getMessage());
            } finally {
                if (snapshot != null) {
                    snapshot.close();
                }
            }


            return bitmap;
        }
    }

    private boolean writeBitmapToFile(Bitmap bitmap, DiskLruCache.Editor editor) {
        synchronized (this) {
            OutputStream out = null;
            try {
                out = new BufferedOutputStream(editor.newOutputStream(0));
                return bitmap.compress(mCompressFormat, mCompressQuality, out);
            } catch (IOException e) {
                Log.e(TAG, "Error msg : " + e.getMessage());
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Error in closing buffer. Msg : " + e.getMessage());
                    }
                }
            }

            return false;
        }
    }

    /**
     * check if bitmap with url for key exist
     * @param url
     * @return
     */
    public boolean containsKey(String url) {
        synchronized (this) {
            url = crateBase64String(url);
            boolean contained = false;
            DiskLruCache.Snapshot snapshot = null;
            try {
                snapshot = mDiskLruCache.get(url);
                contained = snapshot != null;
            } catch (IOException e) {
                Log.e(TAG, "Error msg : " + e.getMessage());
            } finally {
                if (snapshot != null) {
                    snapshot.close();
                }
            }

            return contained;
        }
    }

    private static final char[] DIGITS
            = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String crateBase64String(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public File getCacheFolder() {
        return mDiskLruCache.getDirectory();
    }

}
