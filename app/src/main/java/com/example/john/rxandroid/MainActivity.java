package com.example.john.rxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_common)
    public void common(View v) {
        switch (v.getId()) {
            case R.id.bt_common:
                startActivity(new Intent(this, CommonActivity.class));
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.bt_operator)
    void bt_operator() {
        startActivity(new Intent(this, OperatorsActivity.class));
    }

    @OnClick(R.id.bt_scheduler)
    void bt_scheduler() {
        startActivity(new Intent(this, SchedulerActivity.class));
    }

}
