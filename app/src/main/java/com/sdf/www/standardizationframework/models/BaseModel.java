package com.sdf.www.standardizationframework.models;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;

import com.sdf.www.standardizationframework.utils.BitmapCache;
import com.sdf.www.standardizationframework.utils.GsonRequest;
import com.sdf.www.standardizationframework.utils.RequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by shadow on 16/8/22.
 */

public abstract class BaseModel {
    /**
     * @param url 图片的URL
     * 如果要压缩, 为压缩的最大宽高
     * @param maxWidth
     * @param maxHeight
     */
    public void loadImage(
            final String url , int maxWidth, int maxHeight,
            Response.Listener<Bitmap> listener,
            Response.ErrorListener errorListener){
        //图片缓存
        Bitmap bitmap = BitmapCache.getBitmapCache().getBitmap(url);
        if(bitmap!=null){
            listener.onResponse(bitmap);
            return;
        }
        ImageRequest request = new ImageRequest(
                url,
                listener,
                maxWidth,
                maxHeight,
                Bitmap.Config.ARGB_8888,
                errorListener
        );
        RequestQueue.getmQueue().add(request);

    }
    public void loadImage(String url,
                          Response.Listener<Bitmap> listener,
                          Response.ErrorListener errorListener){
        Bitmap bitmap = BitmapCache.getBitmapCache().getBitmap(url);
        if(bitmap!=null){
            listener.onResponse(bitmap);
            return;
        }
        ImageRequest request = new ImageRequest(
                url,
                listener,
                0,
                0,
                Bitmap.Config.ARGB_8888,
                errorListener
        );
        RequestQueue.getmQueue().add(request);
    }

    /**
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @param <T>
     */
    public<T> void requestGson(String url, Class<T> clazz,
                               Response.Listener<T> listener,
                               Response.ErrorListener errorListener){

        GsonRequest<T> request = new GsonRequest<T>(
                url,
                clazz,
                errorListener,
                listener

        );
        RequestQueue.getmQueue().add(request);
    }

}
