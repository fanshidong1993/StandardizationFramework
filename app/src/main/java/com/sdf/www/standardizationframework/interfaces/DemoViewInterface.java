package com.sdf.www.standardizationframework.interfaces;

import android.graphics.Bitmap;

import com.sdf.www.standardizationframework.bean.DemoBean;

/**
 * Created by shadow on 16/8/22.
 */

public interface DemoViewInterface {
    /**
     * @param str
     * @param length
     */
    /*
    * 用Toast展示一段文字
    * */
    void showToast(String str, int length);

    /**
     * @param str
     * @param length
     */
    /*
    * 用Dialog展示一段文字
    * */
    void showDialog(String str, int length);
    /*
    * 展示倒计时(演示耗时操作)
    * */
    void showCountDown(int count);

    /**
     * 展示网络请求得到的数据
     * @param msg 网络请求抓回来的数据
     */
    void showMessage(DemoBean msg);

    void loagImage(Bitmap bitmap);

}
