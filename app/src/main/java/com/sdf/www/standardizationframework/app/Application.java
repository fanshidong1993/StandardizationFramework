package com.sdf.www.standardizationframework.app;

import com.sdf.www.standardizationframework.utils.RequestQueue;

/**
 * Created by shadow on 16/8/22.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RequestQueue.init(getApplicationContext());
    }
}