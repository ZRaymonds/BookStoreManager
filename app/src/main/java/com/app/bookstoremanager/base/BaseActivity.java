package com.app.bookstoremanager.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.common.rx.RxManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseActivity extends AppCompatActivity {

    public RxManager mRxManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        mRxManager = new RxManager();
        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);

    public abstract int getLayoutResId();

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Activity跳转
     *
     * @param aClass
     */
    public void startActivity(Class<?> aClass) {
        super.startActivity(new Intent(BaseActivity.this, aClass));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
