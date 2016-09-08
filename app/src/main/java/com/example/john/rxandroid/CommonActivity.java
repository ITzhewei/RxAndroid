package com.example.john.rxandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.john.rxandroid.service.CaluService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommonActivity extends AppCompatActivity {

    private CaluService mCaluService;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common2);
        ButterKnife.bind(this);

        mCaluService = new CaluService();
        mDialog = new ProgressDialog(this);

    }

    @OnClick(R.id.bt_listen)
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

    //    private void getResult() {
    //        mDialog.show();
    //        int calc = mCaluService.calc(10, 5);
    //        mDialog.dismiss();
    //        Toast.makeText(CommonActivity.this, "result" + calc, Toast.LENGTH_SHORT).show();
    //    }

    /**
     * 异步得到结果
     */
    private void getResultAsync() {
        //        mCaluService.calcAsync(10, 0, this);
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
