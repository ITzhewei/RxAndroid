package com.example.john.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_scheduler)
    void scheduler() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //在子线程中处理
                Log.d("aaa", "call: " + Thread.currentThread().getName());
                subscriber.onNext("asd");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) //让被观察者执行在异步线程-->可以被调用多次
                .observeOn(AndroidSchedulers.mainThread())//让Observer执行在主线程-->一般只调用一次
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d("aaa", "onNext: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
