package com.sdf.www.standardizationframework.utils;

import android.content.Context;

import com.android.volley.toolbox.Volley;

/**
 * RequestQueue单例
 * Created by shadow on 16/8/22.
 */

public class RequestQueue {
    private static com.android.volley.RequestQueue mQueue ;
    public static void init(Context context){
        mQueue = Volley.newRequestQueue(context);
    }
    public static com.android.volley.RequestQueue getmQueue() {
        return mQueue;
    }
}
