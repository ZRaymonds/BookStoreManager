package com.app.bookstoremanager.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.bookstoremanager.BuildConfig;
import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseActivity;
import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.presenter.UpdatePresenter;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.utils.ToastUtil;
import com.app.bookstoremanager.view.IUpdateView;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity implements IUpdateView {

    @BindView(R.id.RLCheckSetting)
    RelativeLayout RLCheckSetting;


    @BindView(R.id.tv_showVersion)
    TextView tv_showVersion;

    private UpdatePresenter updatePresenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        updatePresenter = new UpdatePresenter(this);
        try {
            tv_showVersion.setText("当前版本:" + BuildConfig.VERSION_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.RLCheckSetting)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RLCheckSetting:
                updatePresenter.getUpdate();
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtil.show(this, "当前已是最新版本！");
    }

    @Override
    public void showUpdateDialog(final String fileUrl) {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.ic_launcher).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadNewVersionProgress(fileUrl);//下载最新的版本程序
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        // 显示对话框
        alertDialog.show();
    }

    @Override
    public void showUpdateError(Exception e) {
        ToastUtil.show(this, e.getMessage());
        LogUtil.d("TAG", e.getMessage());
    }

    /**
     * 下载新版本程序
     *
     * @param fileUrl
     */
    private void loadNewVersionProgress(String fileUrl) {
        final String uri = "http://111.230.204.150" + fileUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        pd.setCancelable(false);
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
//                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        startActivity(intent);
        startActivity(Intent.createChooser(intent, "installApk"));
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
//            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), "bookstore.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

}
