package com.example.john.rxandroid.service;

import android.os.SystemClock;

/**
 * Created by ZheWei on 2016/9/8.
 * 在网络上计算-->M请求数据
 */
public class CaluService {

    int result;

    /*
    计算每个亲戚分多少房子
     */
    public int calc(final int total, final int pCount) {

        //开启异步任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                //数据库查询
                //文件读写
                try {
                    SystemClock.sleep(2000);
                    result = total / pCount;
                    if (mListener != null) {
                        mListener.onSuccess(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (mListener != null) {
                        mListener.onFailed();
                    }
                }

            }
        }).start();
        return result;
    }

    /**
     * 异步调用,并回调通知
     */
    public int calcAsync(final int total, final int pCount, final OnResultListener listener) {


        //开启异步任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                //数据库查询
                //文件读写
                try {
                    SystemClock.sleep(2000);
                    result = total / pCount;
                    listener.onSuccess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailed();
                }

            }
        }).start();
        return result;
    }

    public OnResultListener mListener;

    public void setOnResultListener(OnResultListener listener) {
        mListener = listener;
    }

    /**
     * 面向接口编程
     */
    public interface OnResultListener {
        void onSuccess(int result);

        void onFailed();
    }
}


