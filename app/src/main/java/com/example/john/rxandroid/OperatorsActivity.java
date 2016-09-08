package com.example.john.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_map)
    void clickMap() {
        Observable.just(6)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("aaa", "call: " + s);
                    }
                });
    }

}
