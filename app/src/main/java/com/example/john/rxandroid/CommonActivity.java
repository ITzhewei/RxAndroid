package com.example.john.rxandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.john.rxandroid.service.CaluService;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;


public class CommonActivity extends AppCompatActivity {

    private CaluService mCaluService;
    ProgressDialog mDialog;
    private Subscription mSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common2);
        ButterKnife.bind(this);

        mCaluService = new CaluService();
        mDialog = new ProgressDialog(this);

    }

    @OnClick(R.id.bt_listen1)
    void listen(View v) {
        //10套房子 , 5个亲戚,计算分多少房子
        //                getResult();
        mDialog.show();
        //        getResultAsync();
        mCaluService.calc(10, 5);
        mCaluService.setOnResultListener(new CaluService.OnResultListener() {
            @Override
            public void onSuccess(final int result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        Toast.makeText(CommonActivity.this, "result" + result, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        Toast.makeText(CommonActivity.this, "result,失败了", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 基本实现案例
     */
    @OnClick(R.id.bt_listen2)
    void listen2() {
        //工厂中的流水线对象
        //创建一个Observable对象--->被观察者/可观察对象
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //onNext 可以调用多次
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("10/2");
                //事件序列结束标志
                subscriber.onCompleted();
                //异常
                subscriber.onError(new RuntimeException("RunTime错误啊"));
            }
        }).subscribe(new Observer<String>() {//指定观察者,被观察者必须指定观察者,整个事件才能进行
            @Override
            public void onNext(String s) {
                Log.d("aaa", "onNext: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d("aaa", "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 变化1-->对被观察者进行一个变形
     */
    @OnClick(R.id.bt_listen3)
    void listen3() {
        Observable.just(1, 2).subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Log.d("aaa", "onNext: " + integer);
            }

            @Override
            public void onCompleted() {
                Log.d("aaa", "onNext: ");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 变化2--->对观察者的一个变形
     */
    @OnClick(R.id.bt_listen4)
    void listen4() {
        String[] array = {"123", "456"};
        //订阅者
        mSubscribe = Observable.from(array).subscribe(new Subscriber<String>() { //订阅者
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
        mSubscribe.unsubscribe();//取消订阅
    }

    /**
     * 变化三
     */
    @OnClick(R.id.bt_listen5)
    void listen5() {
        String[] array = {"2222", "32323", "dasdasd"};
        Observable.from(array).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {//onnext闭包-->将操作以对象的形式传过来
                Log.d("aaa", "call: " + s);
            }
        }, new Action1<Throwable>() {//失败
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() { //结束
            @Override
            public void call() {
                Log.d("aaa", "call: complete");
            }
        });
    }

    /**
     * 变化4
     */
    @OnClick(R.id.bt_listen6)
    void listen6() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSubscribe != null && mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }

    /**
     * 需要被回调 成功的方法
     */
  /*  @Override
    public void onSuccess(final int result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Toast.makeText(CommonActivity.this, "result" + result, Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /**
     * 需要被回调 失败的方法
     */
 /*   @Override
    public void onFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                Toast.makeText(CommonActivity.this, "result,失败了", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
