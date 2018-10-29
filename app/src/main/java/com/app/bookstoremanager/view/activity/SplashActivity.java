package com.app.bookstoremanager.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.start_skip_count_down)
    TextView mCountDownTextView;

    private MyCountDownTimer mCountDownTimer;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mCountDownTextView.setText("3s | 跳过");
        mCountDownTimer = new MyCountDownTimer(4000,1000);
        mCountDownTimer.start();
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        },3000);
    }

    @OnClick(R.id.start_skip_count_down)
    public void onClick(View v){
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        if (runnable != null){
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture
         *      表示以「 毫秒 」为单位倒计时的总数
         *      例如 millisInFuture = 1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick()
         *      例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
         *
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            mCountDownTextView.setText("0s 跳过");
        }

        public void onTick(long millisUntilFinished) {
            mCountDownTextView.setText( millisUntilFinished / 1000 + "s | 跳过");
        }

    }

}
