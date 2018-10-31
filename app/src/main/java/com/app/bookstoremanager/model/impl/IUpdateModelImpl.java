package com.app.bookstoremanager.model.impl;

import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.IUpdateModel;
import com.app.bookstoremanager.network.ServerApi;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.utils.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class IUpdateModelImpl implements IUpdateModel {


    @Override
    public void getUpdateInfo(final OnNetListener<UpdateInfo> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.getUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        onNetListener.onSuccess(updateInfo);
                    }
                });
    }
}
