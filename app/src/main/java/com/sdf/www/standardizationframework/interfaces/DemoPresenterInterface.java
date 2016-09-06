package com.sdf.www.standardizationframework.interfaces;

import android.graphics.Bitmap;

import com.sdf.www.standardizationframework.bean.DemoBean;

/**
 * Created by shadow on 16/8/22.
 */

public interface DemoPresenterInterface {
    /**
     * 完成字符串长度的计算
     * @param str 字符串
     * @param length 字符串的长度
     */
    void completeStringCount(String str, int length);

    /**
     * 完成1秒的计数
     * @param count 当前的计数
     */
    void completeCountDownASecend(int count);

    /**
     * 完成网络请求
     * @param result 网络请求的结果
     */
    void completeRequest(DemoBean result);

    void completeImageRequest(Bitmap bitmap);
}
