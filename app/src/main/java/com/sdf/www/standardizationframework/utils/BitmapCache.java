package com.sdf.www.standardizationframework.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 图片缓存类
 * Created by shadow on 16/8/22.
 */

public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> cache;
    private static BitmapCache bitmapCache;

    public static BitmapCache getBitmapCache() {
        if (bitmapCache == null){
            bitmapCache = new BitmapCache();
        }
        return bitmapCache;
    }

    public BitmapCache() {
        cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
