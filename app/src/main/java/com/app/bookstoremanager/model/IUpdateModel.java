package com.app.bookstoremanager.model;

import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.callBack.OnNetListener;

public interface IUpdateModel {

    void getUpdateInfo(final OnNetListener<UpdateInfo> onNetListener);

}
