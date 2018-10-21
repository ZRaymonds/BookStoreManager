package com.app.bookstoremanager.presenter;


import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.IUpdateModel;
import com.app.bookstoremanager.model.impl.IUpdateModelImpl;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.view.IUpdateView;
import com.google.gson.Gson;


public class UpdatePresenter {

    private IUpdateModel iUpdateModel;

    private IUpdateView iUpdateView;

    public UpdatePresenter(IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
        iUpdateModel = new IUpdateModelImpl();
    }

    public void getUpdate() {
        iUpdateModel.getUpdateInfo(new OnNetListener<UpdateInfo>() {
            @Override
            public void onSuccess(UpdateInfo updateInfo) {
                iUpdateView.showMessage(updateInfo);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
