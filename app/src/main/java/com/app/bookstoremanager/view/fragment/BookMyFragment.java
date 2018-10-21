package com.app.bookstoremanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseFragment;
import com.app.bookstoremanager.view.activity.LoginActivity;
import com.app.bookstoremanager.view.activity.SettingActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookMyFragment extends BaseFragment {

    @BindView(R.id.iv_loginView)
    ImageView iv_loginView;

    @BindView(R.id.iv_setting)
    ImageView iv_setting;

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_book_my,null);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_book_my;
    }

    @OnClick({R.id.iv_loginView,R.id.iv_setting})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_loginView:
                startActivity(new Intent(mActivity,LoginActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(mActivity,SettingActivity.class));
                break;
        }
    }

}
