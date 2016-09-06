package com.sdf.www.standardizationframework.presenters;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sdf.www.standardizationframework.bean.DemoBean;
import com.sdf.www.standardizationframework.interfaces.DemoPresenterInterface;
import com.sdf.www.standardizationframework.interfaces.DemoViewInterface;
import com.sdf.www.standardizationframework.models.DemoModel;
import com.sdf.www.standardizationframework.utils.BitmapCache;

/**
 * Created by shadow on 16/8/22.
 */

public class DemoPresenter implements DemoPresenterInterface {
    /*
    * 在View 已经实现了的接口
    * */
    private DemoViewInterface demoViewInterface;

    // TODO: 实现DemoModel
    private DemoModel demoModel;
    /*
    * 判断是用吐司还是弹窗显示的变量
    * */
    private Boolean isToast;

    /**
     * 构造函数
     * @param demoViewInterface
     */
    public DemoPresenter(DemoViewInterface demoViewInterface){
        this.demoViewInterface = demoViewInterface;
        demoModel = new DemoModel(this);//初始化Model
        isToast = true;//设置一个初始值
    }
    /*
    * 以下是创建供View调用的方法,具体的实现,要完成Model后才可以操作
    * */
    public void clickShowToast(String str){
        isToast = true;
        demoModel.calculationStringLength(str);
    }
    public void clickShowDialog(String str){
        isToast = false;
        demoModel.calculationStringLength(str);
    }
    public void clickCountDownStart(){
        demoModel.countDownASecond();
    }
    public void clickCountDownRestart(){
        demoModel.countDownReset();
    }
    public void clickRequest(){
        demoModel.request();
    }
    public void clickImageRequest(){
        final String URL = "http://img.blog.csdn.net/20151114133731501";
        demoModel.loadImage(URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        BitmapCache.getBitmapCache().putBitmap(URL,bitmap);
                        demoViewInterface.loagImage(bitmap);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
    }
    /*
    * 以下是对DemoPresenterInterface的实现
    * */
    @Override
    public void completeStringCount(String str, int length) {
        if (isToast) demoViewInterface.showToast(str,length);
        else demoViewInterface.showDialog(str,length);
    }

    @Override
    public void completeCountDownASecend(int count) {
        demoViewInterface.showCountDown(count);
    }

    @Override
    public void completeRequest(DemoBean result) {
        demoViewInterface.showMessage(result);
    }

    @Override
    public void completeImageRequest(Bitmap bitmap) {
        demoViewInterface.loagImage(bitmap);
    }
}
