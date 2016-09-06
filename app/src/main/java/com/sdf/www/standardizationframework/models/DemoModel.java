package com.sdf.www.standardizationframework.models;

import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sdf.www.standardizationframework.bean.DemoBean;
import com.sdf.www.standardizationframework.interfaces.DemoPresenterInterface;
import com.sdf.www.standardizationframework.utils.BitmapCache;
import com.sdf.www.standardizationframework.utils.GsonRequest;
import com.sdf.www.standardizationframework.utils.RequestQueue;

import org.json.JSONObject;



/**
 * Created by shadow on 16/8/22.
 */

public class DemoModel extends BaseModel {

    private final int MAX_COUNTER = 10;
    private DemoPresenterInterface demoPresenterInterface;
    private int counter;
    public DemoModel(DemoPresenterInterface demoPresenterInterface){
        this.demoPresenterInterface = demoPresenterInterface;
        resetCounter();
    }

    private void resetCounter() {
        counter = MAX_COUNTER;
    }

    public void calculationStringLength(String str){

        demoPresenterInterface.completeStringCount(str,str.length());

    }
    /*
    * 子线程不能直接操作UI线程,通过handler解决
    * */
    private final android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            demoPresenterInterface.completeCountDownASecend(counter);
            super.handleMessage(msg);
        }
    };
    public void countDownASecond(){
        //耗时操作, 开个线程
        new Thread(){
            @Override
            public void run() {
                while (counter > 0)
                try {
                    Thread.sleep(1000);
                    counter --;
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void countDownReset(){
        resetCounter();
        demoPresenterInterface.completeCountDownASecend(counter);
    }
    public void request(){
        //请求数据的URL
        String URL = "http://www.icityto.com/X_UserLogic/yesicity2015/nickname_Page/?Uid=yesicity2015&Field_YHID=559&Yesicity=1&Id=552";
        /*JsonObjectRequest request = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //请求成功回调
                        demoPresenterInterface.completeRequest(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //请求失败回调
                        demoPresenterInterface.completeRequest(volleyError.toString());
                    }
                });*/
        GsonRequest<DemoBean> request = new GsonRequest<DemoBean>(
                URL,
                DemoBean.class,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                },
                new Response.Listener<DemoBean>() {
                    @Override
                    public void onResponse(DemoBean demoBean) {
                        demoPresenterInterface.completeRequest(demoBean);
                    }
                }

        );
        RequestQueue.getmQueue().add(request);
    }

    /**
     * @param url
     * @param maxWidth
     * @param maxHeight
     *//*
    public void loadImage(final String url , int maxWidth, int maxHeight){
       // final String URL = "http://img.blog.csdn.net/20151114133731501";
        //图片缓存
        Bitmap bitmap = BitmapCache.getBitmapCache().getBitmap(url);
        if(bitmap!=null){
            demoPresenterInterface.completeImageRequest(bitmap);
            return;
        }
        ImageRequest request = new ImageRequest(
                url,
                new Response.Listener< Bitmap>(){
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        BitmapCache.getBitmapCache().putBitmap(url,bitmap);
                        demoPresenterInterface.completeImageRequest(bitmap);

                    }
                },
                maxWidth,
                maxHeight,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }
        );
        RequestQueue.getmQueue().add(request);

    }*/
}
