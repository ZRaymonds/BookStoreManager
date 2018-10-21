package com.app.bookstoremanager.view.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseActivity;
import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.presenter.UpdatePresenter;
import com.app.bookstoremanager.utils.ToastUtil;
import com.app.bookstoremanager.view.IUpdateView;


import butterknife.BindView;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity implements IUpdateView {

    @BindView(R.id.RLCheckSetting)
    RelativeLayout RLCheckSetting;


    @BindView(R.id.tv_showVersion)
    TextView tv_showVersion;

    private Context mContext;

    private UpdatePresenter updatePresenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        updatePresenter = new UpdatePresenter(this);
        try {
            tv_showVersion.setText("当前版本:"+getVersionName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.RLCheckSetting)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.RLCheckSetting:
                updatePresenter.getUpdate();
                break;
        }
    }

    /*
     * 获取当前程序的版本号
     */
    public static int getVersionCode(Context ctx) {
        // 获取packagemanager的实例
        int version = 0;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            //getPackageName()是你当前程序的包名
            PackageInfo packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            version = packInfo.versionCode;
            Log.e("TAG", "版本号" + packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /*
     * 获取当前程序的版本名
     */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionName;

    }

    @Override
    public void showMessage(UpdateInfo updateInfo) {
        ToastUtil.show(this,updateInfo.getData().getList().toString());
    }

}
