package com.app.bookstoremanager.presenter;


import com.app.bookstoremanager.BuildConfig;
import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.impl.UpdateModelImpl;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.view.IUpdateView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class UpdatePresenter {

    private UpdateModelImpl iUpdateModel;

    private IUpdateView iUpdateView;

    private List<UpdateInfo.DataBean.ListBean> listBeans = new ArrayList<>();

    public UpdatePresenter(IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
        iUpdateModel = new UpdateModelImpl();
    }

    public void getUpdate() {
        iUpdateModel.getUpdateInfo(new OnNetListener<UpdateInfo>() {
            @Override
            public void onSuccess(UpdateInfo updateInfo) {
                UpdateInfo.DataBean dataBean = updateInfo.getData();
                listBeans = dataBean.getList();
                for (int i = 0; i < listBeans.size(); i++) {
                    int version_code = listBeans.get(i).getVersion_code();
                    String file_name = listBeans.get(i).getFile_name();
                    String file_url = listBeans.get(i).getFile_url();
                    if (BuildConfig.VERSION_CODE < version_code){
                        iUpdateView.showUpdateDialog(file_url);
                    }else {
                        iUpdateView.showMessage(updateInfo.toString());
                    }
                    LogUtil.d("TAG", "versionCode is " + version_code);
                    LogUtil.d("TAG", "file_name is " + file_name);
                    LogUtil.d("TAG", "file_url is " + file_url);
                }
            }

            @Override
            public void onFailure(Exception e) {
                iUpdateView.showUpdateError(e);
            }
        });
    }
}
